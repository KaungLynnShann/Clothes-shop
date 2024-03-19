<template>
    <v-app>
      <v-app-bar  app>
        <v-app-bar-title >
        <router-link class="mx-2 navlink"  to="/" >Home</router-link>

        <router-link class="mx-2 navlink" to="/shop">Shop</router-link>
        </v-app-bar-title>
        <v-spacer></v-spacer>
  
        <v-app-bar-title>
          <span class="gold-text">Good Clothes</span>
        </v-app-bar-title>
        <v-spacer></v-spacer>
        <v-btn icon @click="goToLogin">
          <v-icon>mdi-account</v-icon>
        </v-btn>
          <v-badge
              color="red"
              :content="count"
              v-if="choose"
            >
            <v-icon @click="goToCart">mdi-cart</v-icon>
            </v-badge>
      </v-app-bar>
  
      <!-- Footer with icons -->
      <v-footer app>
        <v-row justify="center">
          <v-btn icon>
            <v-icon>mdi-facebook</v-icon>
          </v-btn>
          <v-btn icon>
            <v-icon>mdi-instagram</v-icon>
          </v-btn>
          <v-btn icon>
            <v-icon>mdi-twitter</v-icon>
          </v-btn>
        </v-row>
  
        <v-row justify="center">
          <v-col>
            &copy; 2024 kaung lynn shann.
          </v-col>
        </v-row>
      </v-footer>
    </v-app>
  </template>
  
  <script>
  import {mapState,mapGetters} from 'vuex'
  export default {
    data: () =>({}),
    computed:{
        ...mapState({
            cart:(state)=>state.cart,
            choose:(state)=>state.choose
        }),
        ...mapGetters({
          count:'getCartLength'
        })
    },
    methods: {
      goToLogin() {
        this.$router.push('/login');
      },
      goToCart() {
        if (this.$route.path !== '/cart') {
          this.$router.push('/cart');
        }
      }
    },
  };
  </script>
  
  <style scoped>
  .gold-text {
    color:teal;
  }
  .v-btn:hover {
    background-color: teal;
    color: white !important;
  }
  .navlink{
    text-decoration: none;
    color: rgb(12, 12, 12);
    font-weight: normal; 
  }
  .navlink:hover {
  cursor: pointer;
  text-decoration: underline;
  color: teal;
}
  </style>
  