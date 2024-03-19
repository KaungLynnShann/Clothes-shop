<template>
  <div>
    <v-container fluid>
      <v-row>
        <v-col cols="12" md="8">
          <v-form ref="createCustomerData" v-model="createCustomerData">
            <v-card>

                <v-icon  color="primary" @click="goBack" left>mdi-arrow-left</v-icon>
              <v-card-title>
                <h3>Please Input Your Information</h3>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <!-- customer name -->
                  <v-row>
                    <v-col cols="12">
                      <v-text-field v-model="createDemo.name" :rules="[
                          (v) => !!v || 'Required',
                          (v) =>
                            (v && v.length <= 50) ||
                            'Name must be less than 50 characters',
                        ]" label="Name" outlined clearable></v-text-field>
                    </v-col>
                  </v-row>
                  <!-- customer phone -->
                  <v-row>
                    <v-col cols="12">
                      <v-text-field v-model="createDemo.phNo" type="number" :rules="[
                          (v) => !!v || 'Required',
                          (v) =>
                            (v && v.length <= 50) ||
                            'Phone no must be less than 50 characters',
                        ]" label="Phone" outlined clearable></v-text-field>
                    </v-col>
                  </v-row>
                  <!-- customer address -->
                  <v-row>
                    <v-col cols="12">
                      <v-text-field v-model="createDemo.address" :rules="[
                          (v) => !!v || 'Required',
                          (v) =>
                            (v && v.length <= 50) ||
                            'Address must be less than 50 characters',
                        ]" label="Address" outlined clearable></v-text-field>
                    </v-col>
                  </v-row>

                  <v-row>
                    <v-col cols="12">
                      <v-text-field v-model="createDemo.city" :rules="[
                          (v) => !!v || 'Required',
                          (v) =>
                            (v && v.length <= 50) ||
                            'city must be less than 50 characters',
                        ]" label="City" outlined clearable></v-text-field>
                    </v-col>
                  </v-row>

                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>

                <v-col justify="center">
                  <v-btn color="blue" @click="save" :disabled="!createCustomerData">
                    <span v-if="!loading">Submit</span>
                    <v-progress-circular v-else indeterminate color="primary"></v-progress-circular>
                  </v-btn>
                </v-col>
              </v-card-actions>
              <v-alert class="mt-3" v-show="errorAlert" dense type="error">
                Please input your information!
              </v-alert>
            </v-card>
          </v-form>
        </v-col>

        <v-col cols="12" md="4">
          <BillPayment :disableOrderButton="!showOrderButton" />
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script>
import { mapState } from 'vuex';
import BillPayment from '@/components/BillPayment.vue';
import utils from '@/utils/utils';
import state from '@/store/state';
import swal from 'sweetalert2'
import axios from 'axios';

export default {
  components: {
    BillPayment
  },
  computed: {
    ...mapState(['cartItems']),

  },
  mounted() {
    console.log('cartItems in cash on deli:', this.cartItems);
  },
  data() {
    return {
      localDomain: utils.constant.localDomain,
      createCustomerData: false,
      showOrderButton: false,
      createDemo: {
        name: "",
        phNo: "",
        address: "",
        city: "",
        status: "PROCESSING",
      },
      errorAlert: false,
      loading: false,
      customerList: [],
      shirtList:[],
      pantsList:[],
      bootList:[],
      hatList:[],
    };
  },
  methods: {
  calculateOrderTotal() {
    let total = 0;
    this.cartItems.forEach(item => {
      if (item.itemType === 'shirt') {
        total += item.quantity * item.shirtPrice;
      } else if (item.itemType === 'pants') {
        total += item.quantity * item.pantsPrice;
      } else if (item.itemType === 'boot') {
        total += item.quantity * item.bootPrice;
      } else if (item.itemType === 'hat') {
        total += item.quantity * item.hatPrice;
      }
    });
    return total;
  },

  async save() {
  // Validate customer data first
  if (this.$refs.createCustomerData.validate()) {
    try {
      // Save customer data
      const customerData = {
        name: this.createDemo.name,
        phNo: this.createDemo.phNo,
        address: this.createDemo.address,
        city: this.createDemo.city,
        status: this.createDemo.status,
      };
      const customerResponse = await axios.post('http://localhost:8081/customer/create', customerData);
      if (customerResponse.status === 200) {
        const customerId = customerResponse.data.customerId;

        const orderItems = [];
        const selectedItems = this.cartItems;
        selectedItems.forEach(item => {
          const orderItem = {
            orderItemId: item.itemId,
            quantity: item.quantity
          };

          if (item.itemType === 'shirt' && item.shirtId) {
            orderItem.shirt = {
              shirtId: item.shirtId,
            };
          } else if (item.itemType === 'pants' && item.pantsId) {
            orderItem.pants = {
              pantsId: item.pantsId,
            };
          } else if (item.itemType === 'boot' && item.bootId) {
            orderItem.boot = {
              bootId: item.bootId,
            };
          } else if (item.itemType === 'hat' && item.hatId) {
            orderItem.hat = {
              hatId: item.hatId,
            };
          }

          // Push the constructed orderItem to orderItems array
          orderItems.push(orderItem);
        });

        // Calculate order total
        const orderTotal = this.calculateOrderTotal();

        const orderData = {
          customerData: { customerId },
          orderTotal,
          orderItems
        };
        this.$store.dispatch('createOrder', orderData)
          .then(createdOrder => {
            swal.fire({
              icon: 'success',
              title: 'Success',
              text: 'Order saved successfully!',
            });
            this.cartItems.length = 0;
            state.choose = false;
            this.$router.push({ name: 'shop' });
            console.log('Order created successfully:', createdOrder);
          })
          .catch(error => {
            console.error('Failed to create order:', error);
          });
      }
    } catch (error) {
      console.error('An error occurred:', error);
    }
  }
},
   goBack() {
      this.$router.go(-1); 
    }
  }
  };
</script>
