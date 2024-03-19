<template>
  <div>
    <Appbar/>
    <v-container fluid class="container">
      <!-- Display shirts -->
      <v-row>
        <v-col cols="9">
          <v-row>
        <v-col v-for="shirt in shirtItems" :key="'shirt-' + shirt.shirtId" cols="3">
          <v-card height="500" class="card-container">
            <v-card-text>
              <v-img :src="localDomain + shirt.shirtPhoto" height="320" cover></v-img>
              <v-btn icon class="remove-icon" @click="removeShirt(shirt)">
                <v-icon color="red">mdi-close</v-icon>
              </v-btn>
              <div class="text-h6">Name: {{ shirt.shirtType }}</div>
              <div class="text-body-1">Price: {{ shirt.shirtPrice }} MMK</div>
              <div class="text-body-1">Size: {{ shirt.shirtSize }}</div>
              <span>Quantity: </span>
              <v-btn icon class="button" @click="decreaseShirtQuantity(shirt)">
                <v-icon>mdi-minus</v-icon>
              </v-btn>
              <span>{{ shirt.quantity }}</span>
              <v-btn icon class="button" @click="increaseShirtQuantity(shirt)">
                <v-icon>mdi-plus</v-icon>
              </v-btn>
            </v-card-text>
          </v-card>
        </v-col>
      <!-- Display pants -->
      
        <v-col v-for="pants in pantsItems" :key="'pants-' + pants.pantsId" cols="3">
          <v-card height="500" class="card-container">
            <v-card-text>
              <v-img :src="localDomain + pants.pantsPhoto" height="320" cover></v-img>
              <v-btn icon class="remove-icon" @click="removePants(pants)">
                <v-icon color="red">mdi-close</v-icon>
              </v-btn>
              <div class="text-h6">Name: {{ pants.pantsType }}</div>
              <div class="text-body-1">Price: {{ pants.pantsPrice }} MMK</div>
              <div class="text-body-1">Size: {{ pants.pantsSize }}</div>
              <span>Quantity: </span>
              <v-btn icon class="button" @click="decreasePantsQuantity(pants)">
                <v-icon>mdi-minus</v-icon>
              </v-btn>
              <span>{{ pants.quantity }}</span>
              <v-btn icon class="button" @click="increasePantsQuantity(pants)">
                <v-icon>mdi-plus</v-icon>
              </v-btn>
            </v-card-text>
          </v-card>
        </v-col>
        <!-- Display boot -->
      
        <v-col v-for="boot in bootItems" :key="'boot-' + boot.bootId" cols="3">
          <v-card height="500" class="card-container">
            <v-card-text>
              <v-img :src="localDomain + boot.bootPhoto" height="320" cover></v-img>
              <v-btn icon class="remove-icon" @click="removeBoot(boot)">
                <v-icon color="red">mdi-close</v-icon>
              </v-btn>
              <div class="text-h6">Name: {{ boot.bootType }}</div>
              <div class="text-body-1">Price: {{ boot.bootPrice }} MMK</div>
              <div class="text-body-1">Size: {{ boot.bootSize }}</div>
              <span>Quantity: </span>
              <v-btn icon class="button" @click="decreaseBootQuantity(boot)">
                <v-icon>mdi-minus</v-icon>
              </v-btn>
              <span>{{ boot.quantity }}</span>
              <v-btn icon class="button" @click="increaseBootQuantity(boot)">
                <v-icon>mdi-plus</v-icon>
              </v-btn>
            </v-card-text>
          </v-card>
        </v-col>
        <!-- Display Accessories -->
      
        <v-col v-for="hat in hatItems" :key="'hat-' + hat.hatId" cols="3">
          <v-card height="500" class="card-container">
            <v-card-text>
              <v-img :src="localDomain + hat.hatPhoto" height="320" cover></v-img>
              <v-btn icon class="remove-icon" @click="removeHat(hat)">
                <v-icon color="red">mdi-close</v-icon>
              </v-btn>
              <div class="text-h6">Name: {{ hat.hatType }}</div>
              <div class="text-body-1">Price: {{ hat.hatPrice }} MMK</div>
              <span>Quantity: </span>
              <v-btn icon class="button" @click="decreaseHatQuantity(hat)">
                <v-icon>mdi-minus</v-icon>
              </v-btn>
              <span>{{ hat.quantity }}</span>
              <v-btn icon class="button" @click="increaseHatQuantity(hat)">
                <v-icon>mdi-plus</v-icon>
              </v-btn>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
      </v-col>
        <!-- billpayment -->
        <v-col cols="3">
          <div class="bill-payment-container">
            <BillPayment/>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import Appbar from '@/components/Appbar.vue';
import utils from '@/utils/utils';
import state from '@/store/state';
import BillPayment from '@/components/BillPayment.vue';

export default {
  components: {
    Appbar,BillPayment
  },
  computed: {
    ...mapState(['cartItems']),
    ...mapState(['order']),
    shirtItems() {
      return this.cartItems.filter(item => item.itemType === 'shirt');
    },
    pantsItems() {
      return this.cartItems.filter(item => item.itemType === 'pants');
    },
    bootItems() {
      return this.cartItems.filter(item => item.itemType === 'boot');
    },
    hatItems() {
      return this.cartItems.filter(item => item.itemType === 'hat');
    },
  },
  data() {
    return {
      localDomain: utils.constant.localDomain,
    }
  },
  mounted() {
    console.log('cartItems:', this.cartItems);
    console.log('order in cartPage',this.order);
  },
  methods: {
    // ------------------for shirt---------------------------------
    increaseShirtQuantity(item) {
        this.$store.dispatch('increaseShirtQuantity', item);

      },
      decreaseShirtQuantity(item) {
        this.$store.dispatch('decreaseShirtQuantity', item);
      },
      removeShirt(item) {
        this.$store.dispatch('removeShirt', item);
        if( this.cartItems.length ===0)
        {
          state.choose = false;
          this.$router.push('/shop');
        }
      },
        // ----------------------for pants---------------------------
        increasePantsQuantity(item) {
        this.$store.dispatch('increasePantsQuantity', item);

      },
      decreasePantsQuantity(item) {
        this.$store.dispatch('decreasePantsQuantity', item);
      },
      removePants(item) {
        this.$store.dispatch('removePants', item);
        if( this.cartItems.length ===0)
        {
          state.choose = false;
          this.$router.push('/shop');
        }
      },
       // ----------------------for boot---------------------------
       increaseBootQuantity(item) {
        this.$store.dispatch('increaseBootQuantity', item);

      },
      decreaseBootQuantity(item) {
        this.$store.dispatch('decreaseBootQuantity', item);
      },
      removeBoot(item) {
        this.$store.dispatch('removeBoot', item);
        if( this.cartItems.length ===0)
        {
          state.choose = false;
          this.$router.push('/shop');
        }
      },
       // ----------------------for accessories---------------------------
       increaseHatQuantity(item) {
        this.$store.dispatch('increaseHatQuantity', item);

      },
      decreaseHatQuantity(item) {
        this.$store.dispatch('decreaseHatQuantity', item);
      },
      removeHat(item) {
        this.$store.dispatch('removeHat', item);
        if( this.cartItems.length ===0)
        {
          state.choose = false;
          this.$router.push('/shop');
        }
      },
  },
};
</script>

<style scoped>
.container {
  margin-top: -50%;
}
.card-container {
  position: sticky;
}
.remove-icon {
  position: absolute;
  top: 8px;
  right: 8px;
}
.remove-icon:hover {
  background-color: transparent!important;
}
.button:hover {
  background-color: black!important;
}
.bill-payment-container {
  position: absolute;
  top: 110px;
  right: 0;
  width: 300px;
}
</style>
