User
<template>
  <div>
    <AdminSidebar />
    <v-container class="container">
      <v-row v-for="(order, index) in orders" :key="index">
        <v-col cols="12">
          <v-card v-if="order.customerData">
            <v-card-text>

              <v-row class="mb-2">
                <v-col cols="6">
                  <strong style="color: blue;">Order NO:{{ order.orderId }}</strong>
                </v-col>

                <v-col cols="6" class="text-right">
                  <v-chip :color="getChipColor(order.status)" @click="toggleStatus(order)">
                    <template v-if="!updatingStatus(order.orderId)">
                      <h3>{{ order.status }}</h3>
                    </template>
                  </v-chip>
                  </v-col>
              </v-row>

              <v-spacer></v-spacer>

              <v-row>
                <!-- Customer Information -->
                <v-col cols="4">
                  <v-card-title>Customer Information</v-card-title>
                  <v-card-text>
                    <strong>Name: {{ order.customerData.name }}</strong>
                  </v-card-text>
                  <v-card-text>
                    <strong>Phone: {{ order.customerData.phNo }}</strong>
                  </v-card-text>
                  <v-card-text>
                    <strong>Address: {{ order.customerData.address }}</strong>
                  </v-card-text>
                  <v-card-text>
                    <strong>City: {{ order.customerData.city }}</strong>
                  </v-card-text>
                </v-col>

                <v-spacer></v-spacer>

                <v-col cols="4">
                  <v-card-title>Order Items</v-card-title>
                  <v-list subheader two-line class="mt-1" v-if="order.orderItems && order.orderItems.length">
                    <v-list-item v-for="(item, idx) in order.orderItems" :key="idx">

                      <!-- Display shirt details -->
                      <template v-if="item.shirt">
                        <v-list-item-avatar rounded color="grey lighten-3" width="60" height="auto">
                          <v-img :src="localDomain + item.shirt.shirtPhoto"></v-img>
                        </v-list-item-avatar>
                        <v-list-item-content>
                          <v-list-item-title>Name: {{ item.shirt.shirtType }}</v-list-item-title>
                          <v-list-item-subtitle><strong>
                              {{ item.quantity }} x {{ item.shirt.shirtPrice }} Ks
                              = {{ item.quantity * item.shirt.shirtPrice }}Ks</strong>
                          </v-list-item-subtitle>
                          <v-list-item-title v-if="order.status === 'CONFIRM'">
                            Available Stock: {{ item.shirt.shirtQuantity - item.quantity }}
                          </v-list-item-title>
                        </v-list-item-content>
                      </template>

                      <!-- Display pants details -->
                      <template v-if="item.pants">
                        <v-list-item-avatar rounded color="grey lighten-3" width="60" height="auto">
                          <v-img :src="localDomain + item.pants.pantsPhoto"></v-img>
                        </v-list-item-avatar>
                        <v-list-item-content>
                          <v-list-item-title>Name: {{ item.pants.pantsType }}</v-list-item-title>
                          <v-list-item-subtitle><strong>
                              {{ item.quantity }} x {{ item.pants.pantsPrice }} Ks
                              = {{ item.quantity * item.pants.pantsPrice }}Ks</strong>
                          </v-list-item-subtitle>
                        </v-list-item-content>
                      </template>

                      <!-- Display boot details -->

                      <template v-if="item.boot">
                        <v-list-item-avatar rounded color="grey lighten-3" width="60" height="auto">
                          <v-img :src="localDomain + item.boot.bootPhoto"></v-img>
                        </v-list-item-avatar>
                        <v-list-item-content>
                          <v-list-item-title>Name: {{ item.boot.bootType }}</v-list-item-title>
                          <v-list-item-subtitle><strong>
                              {{ item.quantity }} x {{ item.boot.bootPrice }} Ks
                              = {{ item.quantity * item.boot.bootPrice }}Ks</strong>
                          </v-list-item-subtitle>
                        </v-list-item-content>
                      </template>

                      <!-- Display accessories details -->

                      <template v-if="item.hat">
                        <v-list-item-avatar rounded color="grey lighten-3" width="60" height="auto">
                          <v-img :src="localDomain + item.hat.hatPhoto"></v-img>
                        </v-list-item-avatar>
                        <v-list-item-content>
                          <v-list-item-title>Name: {{ item.hat.hatType }}</v-list-item-title>
                          <v-list-item-subtitle><strong>
                              {{ item.quantity }} x {{ item.hat.hatPrice }} Ks
                              = {{ item.quantity * item.hat.hatPrice }}Ks</strong>
                          </v-list-item-subtitle>
                        </v-list-item-content>
                      </template>
                    </v-list-item>
                  </v-list>
                </v-col>
              </v-row>

              <v-divider class="mx-4"></v-divider>
              <br>
              <v-row>
                <v-col cols="12" class="text-center">
                  <strong>Total Order Bill: {{ order.orderTotal }} ks</strong>
                </v-col>
              </v-row>

            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import AdminSidebar from '@/components/AdminSidebar.vue';
import utils from '@/utils/utils';
import axios from 'axios';

export default {
  components:{
    AdminSidebar,
  },
  data() {
    return {
      localDomain: utils.constant.localDomain,
      orders: [],
      customerList:[],
      statusList: [],
      updatingOrders: {},
    };
  },
  async created() {
    await this.fetchAllOrders();
    await this.fetchAllCustomers();
    await this.fetchAllStatus();
  },
  methods: {

    async fetchAllStatus(){
      const response = await utils.http.get("/admin/status/list");
      if(response){
        const data = await response.json();
        if(data)
         this.statusList = data;
      }
    },
    async fetchAllCustomers(){

      try {
        const resp = await axios.get('http://localhost:8081/admin/customer/list');
        if (resp && resp.status === 200) {
          this.customerList = resp.data;
        }
      } catch (error) {
        console.error('Error fetching orders:', error);
      }

    },
    async fetchAllOrders() {
      try {
        const resp = await axios.get('http://localhost:8081/admin/order/list');
        if (resp && resp.status === 200) {
          this.orders = resp.data;
        }
      } catch (error) {
        console.error('Error fetching orders:', error);
      }
    },
    async toggleStatus(order) {
    const orderId = order.orderId;
    this.updatingOrders[orderId] = true; 
    try {
      const newStatus = order.status === 'ORDER' ? 'CONFIRM' : 'ORDER';
      const response = await axios.put(`http://localhost:8081/admin/update/order_status/${orderId}`, {
        orderId: orderId,
        status: newStatus,
      });
      if (response && response.status === 200) {
        order.status = newStatus;

      } else {
        console.error('Error updating order status:', response);
      }
   } catch (error) {
      console.error('Error updating order status:', error);
    } finally {
      this.updatingOrders[orderId] = false; 
    }
  },

  updatingStatus(orderId) {
    return this.updatingOrders[orderId] || false;
  },
    getChipColor(status) {
      switch (status) {
        case 'ORDER':
          return 'error';
        case 'CONFIRM':
          return 'success';
        default:
          return 'grey'; 
      }
    },
    confirmDeleteOrder(orderId) {
    this.showDeleteDialog = true;
    this.selectedOrderId = orderId;
  },

  closeDeleteDialog() {
    this.showDeleteDialog = false;
  },
}
};
</script>