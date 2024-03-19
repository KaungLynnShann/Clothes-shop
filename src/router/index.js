import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';
import LoginForm from '../views/LoginForm.vue'
import AdminCategoryView from '../views/AdminCategoryView'
import AdminSidebar from '@/components/AdminSidebar.vue';
import AdminShirtView from '../views/AdminShirtView'
import AdminBootView from '../views/AdminBootView'
import AdminRecordView from '../views/AdminRecordView'
import AdminAccessoriesView from '../views/AdminAccessoriesView'
import AdminPantsView from '../views/AdminPantsView'
import ShopView from '../views/ShopView'
import ShirtDetails from '../views/ShirtDetails'
import PantsDetails from '../views/PantsDetails'
import BootDetails from '../views/BootDetails'
import AccessoriesDetails from '../views/AccessoriesDetails'
import AdminShopView from '../views/AdminShopView'
import CartView  from '../views/CartView'
import CashOnDeli from '../views/CashOnDeli'

import state from "@/store/state";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  
  {
    path: "/login",
    name: "Login",
    component: LoginForm,
  },
  {
    path:"/admin/shop",
    name:"admin_shop",
    component:AdminShopView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path:"/admin/category",
    name:"admin_category",
    component:AdminCategoryView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/admin",
    name: "Admin",
    component: AdminSidebar,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path:"/admin/shirt",
    name:"admin_shirt",
    component:AdminShirtView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path:"/admin/boot",
    name:"admin_boot",
    component:AdminBootView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path:"/admin/record/",
    name:"admin_record",
    component:AdminRecordView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path:"/admin/hat",
    name:"admin_hat",
    component:AdminAccessoriesView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path:"/admin/pants",
    name:"admin_pants",
    component:AdminPantsView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path:"/shop",
    name:"shop",
    component:ShopView,
  },
  {
    path:"/cart",
    name:"cart",
    component:CartView,
  },
  {
    path:"/cash",
    name:"cash",
    component:CashOnDeli,
  },
  {
    path: "/ShirtDetails/:shirtId",
    name: "ShirtDetails",
    component:ShirtDetails,
  },
  {
    path: "/PantsDetails/:pantsId",
    name: "PantsDetails",
    component:PantsDetails,
  },
  {
    path: "/BootDetails/:bootId",
    name: "BootDetails",
    component:BootDetails,
  },
  {
    path: "/AccessoriesDetails/:hatId",
    name: "AccessoriesDetails",
    component:AccessoriesDetails,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});
router.beforeEach(async (to, from, next) => {
  let loginUser = state.loginUser;
  let isLogin = sessionStorage.getItem("isLogin");

  // from - Home
  // Need to be login, But is not login
  if (to.meta.requiresAuth == true && !isLogin) {
    next({ path: "/login" });
    console.log(loginUser);
  }
  else {
    next();
  }
});


export default router;
