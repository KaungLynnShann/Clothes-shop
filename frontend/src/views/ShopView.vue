<template>
  <div>
    <Appbar/>

    <!-- Categories Menu -->
    <v-container fluid class="categories">
      <v-row>
        <v-col cols="12">
          <v-card class="mx-auto">
            <v-navigation-drawer permanent class="limit">
              <v-list dense nav class="categories-container">
                <v-list-item v-for="(category, index) in categoryList" 
                :key="index" link 
                @click="onClickCategory(category)">
                  <v-list-item-content>
                    <v-list-item-title>{{ category.categoryName ? category.categoryName.charAt(0).toUpperCase()
                     + category.categoryName.slice(1) : '' }}</v-list-item-title>

                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-navigation-drawer>
          </v-card>
        </v-col>
      </v-row>
    </v-container>

    <!-- Product Listings -->
    <v-container fluid class="product-container">
      <!-- Shirt List -->
      <v-row>
        <v-carousel cycle hide-delimiters ref="carousel">
          <v-carousel-item v-for="(chunk, index) in chunkedShirtList" :key="index">
            <v-row>
              <v-col v-for="(shirt, idx) in chunk" :key="idx" cols="3">
                <v-card class="mx-auto" @click="goToShirtDetails(shirt)" height="450">
                  <v-card-text>
                  <v-img :src="localDomain + shirt.shirtPhoto" height="320" cover></v-img>
                  <!-- Cart icon -->
                      <v-icon class="mdi mdi-cart-outline cart-icon" @click.stop=" addToCart(shirt,'shirt')">
                          mdi-cart
                        </v-icon>
                  <div class="text-h6">Name-{{ shirt.shirtType }}</div>
                  <div class="text-body-1">Type-{{ shirt.shirtCategory.categoryName }}</div>
                  <div class="text-body-1">Price-{{ shirt.shirtPrice }} Kyat</div>
                  <div class="text-body-1">Size-{{ shirt.shirtSize }}</div>
                </v-card-text>
                </v-card>
              </v-col>
            </v-row>
          </v-carousel-item>
        </v-carousel>
      </v-row>
      <!-- Pants List -->
      <v-row>
        <v-carousel cycle hide-delimiters ref="carousel">
          <v-carousel-item v-for="(chunk, index) in chunkedPantsList" :key="index">
            <v-row>
              <v-col v-for="(pants, idx) in chunk" :key="idx" cols="3">
                <v-card  @click="goToPantsDetails(pants)" height="450">
                  <v-card-text>
                  <v-img :src="localDomain + pants.pantsPhoto" height="320" cover></v-img>
                  <!-- Cart icon -->
                  <v-icon class="mdi mdi-cart-outline cart-icon" @click.stop="addToCart(pants,'pants')">
                     mdi-cart
                  </v-icon>
                  <div class="text-h6">Name-{{ pants.pantsType }}</div>
                  <div class="text-body-1">Type-{{ pants.pantsCategory.categoryName }}</div>
                  <div class="text-body-1">Price-{{ pants.pantstPrice }} Kyat</div>
                  <div class="text-body-1">Size-{{ pants.pantsSize }}</div>
                </v-card-text>
                </v-card>
              </v-col>
            </v-row>
          </v-carousel-item>
        </v-carousel>
      </v-row>

      <!-- Boot List -->
      <v-row>
        <v-carousel cycle hide-delimiters ref="carousel">
          <v-carousel-item v-for="(chunk, index) in chunkedBootList" :key="index">
            <v-row>
              <v-col v-for="(boot, idx) in chunk" :key="idx" cols="3">
                <v-card class="mx-auto" @click="goToBootDetails(boot)" height="450">
                  <v-card-text>
                  <v-img :src="localDomain + boot.bootPhoto" height="320" cover></v-img>
                  <!-- Cart icon -->
                      <v-icon class="mdi mdi-cart-outline cart-icon" @click.stop="addToCart(boot,'boot')">
                          mdi-cart
                        </v-icon>
                  <div class="text-h6">Name-{{ boot.bootType }}</div>
                  <div class="text-body-1">Type-{{ boot.bootCategory.categoryName }}</div>
                  <div class="text-body-1">Price-{{ boot.bootPrice }} Kyat</div>
                  <div class="text-body-1">Size-{{ boot.bootSize }}</div>
                </v-card-text>
                </v-card>
              </v-col>
            </v-row>
          </v-carousel-item>
        </v-carousel>
      </v-row>
      <!-- Hat List -->
      <v-row>
        <v-carousel cycle hide-delimiters ref="carousel">
          <v-carousel-item v-for="(chunk, index) in chunkedHatList" :key="index">
            <v-row>
              <v-col v-for="(hat, idx) in chunk" :key="idx" cols="3">
                <v-card @click="goToAccessoriesDetails(hat)" height="450">
                  <v-card-text>
                  <v-img :src="localDomain + hat.hatPhoto" height="320"  cover></v-img>
                  <!-- Cart icon -->
                  <v-icon  class="mdi mdi-cart-outline cart-icon" @click.stop="addToCart(hat,'hat')">
                     mdi-cart
                  </v-icon>
                  <div class="text-h6">Name-{{ hat.hatType }}</div>
                  <div class="text-body-1">Type-{{ hat.hatCategory.categoryName }}</div>
                  <div class="text-body-1">Price-{{ hat.hatPrice }} Kyat</div>
                </v-card-text>
                </v-card>
              </v-col>
            </v-row>
          </v-carousel-item>
        </v-carousel>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import utils from "@/utils/utils";
import Appbar from '@/components/Appbar.vue';
import { mapState } from "vuex";

export default {
  name: "ShopView",

  components: {
    Appbar,
  },

  data() {
    return {
      localDomain: utils.constant.localDomain,
      categoryList: [],
      shirtList: [],
      pantsList: [],
      bootList: [],
      hatList: [],
      // filteredProducts: [],
    };
  },
  computed: {

    ...mapState({
        carts:(state)=>state.cartItems
        // ...mapState(['routePath']), 
    }),
    selectedProduct(){
      let inCart = false;
      this.carts.forEach( item=> {
        if(item.id==this.shirt.shirtId){
          inCart = true;
        }
        if(item.id == this.pants.pantsId){
          inCart = true;
        }
        if(item.id == this.boot.bootId){
          inCart = true;
        }
        if(item.id == this.hat.hatId){
          inCart = true;
        }
      });
      return inCart;
    },

    chunkedShirtList() {
      const chunkSize = 4; 
      const chunkedArray = [];
      for (let i = 0; i < this.shirtList.length; i += chunkSize) {
        chunkedArray.push(this.shirtList.slice(i, i + chunkSize));
      }
      return chunkedArray;
    },
    chunkedPantsList() {
      const chunkSize = 4; 
      const chunkedArray = [];
      for (let i = 0; i < this.pantsList.length; i += chunkSize) {
        chunkedArray.push(this.pantsList.slice(i, i + chunkSize));
      }
      return chunkedArray;
    },
    chunkedBootList() {
      const chunkSize = 4; 
      const chunkedArray = [];
      for (let i = 0; i < this.bootList.length; i += chunkSize) {
        chunkedArray.push(this.bootList.slice(i, i + chunkSize));
      }
      return chunkedArray;
    },
    chunkedHatList() {
      const chunkSize = 4; 
      const chunkedArray = [];
      for (let i = 0; i < this.hatList.length; i += chunkSize) {
        chunkedArray.push(this.hatList.slice(i, i + chunkSize));
      }
      return chunkedArray;
    },
  },

  async created() {
    await this.fetchAllCategories();
    await this.fetchAllShirts();
    await this.fetchAllPants();
    await this.fetchAllBoots();
    await this.fetchAllHats();
  },

  methods: {
    async fetchAllCategories() {
      const resp = await utils.http.get("/category/list/");
      if (resp && resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.categoryList = data;
        }
      }
    },

    async fetchAllShirts() {
      const resp = await utils.http.get("/shirt/list/");
      if (resp && resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.shirtList = data;
        }
      }
    },

    async fetchAllPants() {
      const resp = await utils.http.get("/pants/list/");
      if (resp && resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.pantsList = data;
        }
      }
    },

    async fetchAllBoots() {
      const resp = await utils.http.get("/boot/list/");
      if (resp && resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.bootList = data;
        }
      }
    },

    async fetchAllHats() {
      const resp = await utils.http.get("/hat/list/");
      if (resp && resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.hatList = data;
        }
      }
    },

    goToShirtDetails(shirt) {
      this.$router.push({
        path: "/ShirtDetails/" + shirt.shirtId,
      });
    },

    goToPantsDetails(pants) {
      this.$router.push({
        path: "/PantsDetails/" + pants.pantsId,
      });
    },
    goToBootDetails(boot) {
      this.$router.push({
        path: "/BootDetails/" + boot.bootId,
      });
    },
    goToAccessoriesDetails (hat){
      this.$router.push({
        path: "/AccessoriesDetails/" + hat.hatId,
      });
    },

    async onClickCategory(category) {
      try {
        if (category.categoryName === 'All') {
          // Fetch all products
          await this.fetchAllShirts();
          await this.fetchAllPants();
          await this.fetchAllBoots();
          await this.fetchAllHats();
        } else {
          // Fetch products based on the selected category
          const shirtResponse = await utils.http.get("/shirt/category/" + category.categoryId);
          const pantsResponse = await utils.http.get("/pants/category/" + category.categoryId);
          const bootResponse = await utils.http.get("/boot/category/" + category.categoryId);
          const hatResponse = await utils.http.get("/hat/category/" + category.categoryId);

          if (shirtResponse.status === 200) {
            const shirtData = await shirtResponse.json();
            if (shirtData) {
              this.shirtList = shirtData;
            }
          }

          if (pantsResponse.status === 200) {
            const pantsData = await pantsResponse.json();
            if (pantsData) {
              this.pantsList = pantsData;
            }
          }

          if (bootResponse.status === 200) {
            const bootData = await bootResponse.json();
            if (bootData) {
              this.bootList = bootData;
            }
          }

          if (hatResponse.status === 200) {
            const hatData = await hatResponse.json();
            if (hatData) {
              this.hatList = hatData;
            }
          }
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    },
    addToCart(product, type) {
      product.quantity = 1;
      product.itemType = type; 
      switch (type) {
        case 'shirt':
          this.$store.commit("ADD_SHIRT", product);
          break;
        case 'pants':
          this.$store.commit("ADD_PANTS", product);
          break;
        case 'boot':
          this.$store.commit("ADD_BOOT", product);
          break;
        case 'hat':
          this.$store.commit("ADD_HAT", product);
          break;
        default:
          console.error("Invalid product type");
      }
    },
    previousShirts() {
      this.$refs.carousel.prev();
    },

    nextShirts() {
      this.$refs.carousel.next();
    },
  },
};
</script>
<style scoped>
.categories{
  margin-top: -50%;
}
.limit{
  width: auto !important;
}
 .categories-container {
  display: flex;
}
.product-container {
  overflow-x: auto;
}

.product-container::-webkit-scrollbar {
  display: none; 
}
.cart-icon {
  position: absolute;
  top: 20px; 
  right: 20px; 
  color: white; 
  background-color: tomato; 
  border: none; 
  border-radius: 50%;
  padding: 5px;
  transition: background-color 0.3s, color 0.3s;
}
.cart-icon:hover {
  background-color: white;
  color: tomato; 
}
</style>