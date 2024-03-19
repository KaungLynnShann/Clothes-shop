<template>
  <v-navigation-drawer
    v-model="drawer"
    app
    color="white"
    mini-variant
    mini-variant-width="90"
  >
  <v-list flat>
    <v-list-item-group v-model="selectedItem">
      <v-list-item to="/admin/shop">
          <v-list-item-content>
            <v-avatar class="d-block text-center mx-auto" size="40">
              <v-icon color="primary">fas fa-store</v-icon>
            </v-avatar>
          </v-list-item-content>
        </v-list-item>
        <v-list-item
          v-for="(item, i) in items"
          :key="i"
          active-class="border"
          :ripple="false"
          :to="item.route"
        >
        <v-list-item-content>
            <v-icon >{{ item.icon }}</v-icon>
           <v-list-item-subtitle
              align="center"
              class="mt-3 caption"
            >{{ item.text }}</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
    </v-list-item-group>
</v-list>
<v-spacer></v-spacer>
<div
      style="
        position: absolute;
        bottom: 20px;
        margin-left: auto;
        margin-right: auto;
        left: 0;
        right: 0;
        text-align: center;
      "
    >
      <a class="a-logout" href="" @click="logout()">
        <v-icon>logout</v-icon><br /><span>Logout</span></a
      >
    </div>
  </v-navigation-drawer>
</template>

<script>
import utils from '@/utils/utils';
export default {
  data: () => ({
    localDomain: utils.constant.localDomain,
    loginUser: {},
    isLogin: false,
    selectedItem: 0,
    drawer: null,
    items: [
      { icon: "fa-list", text: "Category", route: "/admin/category" },
      { icon: "fa-shirt", text: "Shirt", route: "/admin/shirt" },
      { icon: "mdi-hanger", text: "Pants", route: "/admin/pants" },
      { icon: "mdi-shoe-sneaker", text: "Boot", route: "/admin/boot" },
      { icon: "mdi-bag-personal", text: "Accessories", route: "/admin/hat" },
      { icon: "mdi-clipboard-text", text: "Record", route: "/admin/record" },
     
    ],
  }),
  created() {
    // Login User
    this.loginUser = this.$store.state.loginUser;
    this.$store.watch(
      () => {
        return this.$store.state.loginUser;
      },
      (newVal) => {
        this.loginUser = newVal;
      },
      {
        deep: true,
      }
    );
    // IsLogin
    this.isLogin = this.$store.state.isLogin;
    this.$store.watch(
      () => {
        return this.$store.state.isLogin;
      },
      (newVal) => {
        this.isLogin = newVal;
      },
      {
        deep: true,
      }
    );
  },
  methods: {
    logout() {
      this.$store.commit("logout");
      if (this.$route.path != "/login") {
        this.$router.push({ path: "/" });
        this.$router.go();
      }
    },
  },
};
</script>

<style>
sidebar-nav-list {
  text-decoration: none;
}
.border {
  margin-left: 8px;
  margin-right: 8px;
  background: teal;
  border-radius: 20px;
  text-decoration: none;
}
.v-list-item-group .v-list-item--active,
.sidebar-nav-list {
  color: #ffffff !important;
}
.theme--light.v-list-item--active .v-list-item__subtitle,
.theme--light.v-list-item .v-list-item__action-text {
  color: #ffffff !important;
}
.a-logout {
  text-decoration: none;
  font-family: "Ubuntu", sans-serif !important;
  font-weight: bolder;
  font-size: small;
}
</style>
