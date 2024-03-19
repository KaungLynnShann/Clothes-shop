// import state from "./state";
import swal from "sweetalert2";

export const setLoginUser = (state, user) => {
    sessionStorage.setItem("loginUser", JSON.stringify(user));
    sessionStorage.setItem("isLogin", true);
    state.loginUser = user;
    state.isLogin = true;
  };
  export const logout = (state) => {
    sessionStorage.removeItem("loginUser");
    sessionStorage.removeItem("isLogin");
    state.loginUser = {};
    state.isLogin = false;
  };
  // --------------------------------for shirt-----------------------------------
    export const ADD_SHIRT = (state,shirt) => {
     state.choose = true;
     state.cartItems.push(shirt);
  };
  export const SET_SHIRTS = (state, shirts) => {
    state.shirts = shirts;
  };
  export const REMOVE_SHIRT_FROM_ORDER = (state, shirt) => {
    state.order.orderItems =
      state.order.orderItems.filter((item) => {
        return item.shirt.shirtId !== shirt.shirtId;
      });
  };
  export const REMOVE_SHIRT = (state, removeProduct) => {
    const shirtId = removeProduct.shirtId; 
    state.cartItems = state.cartItems.filter(item => item.shirtId !== shirtId);
  };
  export const INCREASE_SHIRT_QUANTITY = (state, shirt) => {
    const foundShirt = state.cartItems.find(item => item.shirtId === shirt.shirtId);
    if (foundShirt.quantity < foundShirt.shirtQuantity) {
      state.cartItems = state.cartItems.map(item => {
        if (item.shirtId === shirt.shirtId) {
          item.quantity += 1;
          // item.shirtQuantity -= 1;
        }
        return item;
      });
    }
    else{
      swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Sorry, the stock is over for this item!",
      });
    }
  };
  
  export const DECREASE_SHIRT_QUANTITY = (state, shirt) => {
    const foundShirt = state.cartItems.find(item => item.shirtId === shirt.shirtId);
    if (foundShirt && foundShirt.shirtQuantity > 1 && foundShirt.quantity > 1 ) {
      state.cartItems = state.cartItems.map(item => {
        if (item.shirtId === shirt.shirtId) {
          item.quantity -= 1;
        }
        return item;
      });
    }
  };
  // --------------------------------for shirt-----------------------------------
  export const ADD_PANTS = (state,pants) => {
    state.choose = true;
    state.cartItems.push(pants);
  };
  export const SET_PANTS = (state, pants) => {
    state.pants = pants;
  };
  export const REMOVE_PANTS_FROM_ORDER = (state, pants) => {
    state.order.orderItems =
      state.order.orderItems.filter((item) => {
        return item.pants.pantsId !== pants.pantsId;
      });
  };
  export const REMOVE_PANTS = (state, removeProduct) => {
    const pantsId = removeProduct.pantsId; 
    state.cartItems = state.cartItems.filter(item => item.pantsId !== pantsId);
  };
  export const INCREASE_PANTS_QUANTITY = (state, pants) => {
    const foundPants = state.cartItems.find(item => item.pantsId === pants.pantsId);
    if (foundPants.quantity < foundPants.pantsQuantity) {
      state.cartItems = state.cartItems.map(item => {
        if (item.pantsId === pants.pantsId) {
          item.quantity += 1;
          // item.shirtQuantity -= 1;
        }
        return item;
      });
    }
    else{
      swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Sorry, the stock is over for this item!",
      });
    }
  };
  
  export const DECREASE_PANTS_QUANTITY = (state, pants) => {
    const foundPants = state.cartItems.find(item => item.pantsId === pants.pantsId);
    if (foundPants && foundPants.pantsQuantity > 1 && foundPants.quantity > 1 ) {
      state.cartItems = state.cartItems.map(item => {
        if (item.pantsId === pants.pantsId) {
          item.quantity -= 1;
        }
        return item;
      });
    }
  };
// ---------------------------for boot-------------------------------
  export const ADD_BOOT = (state,boot) => {
    state.choose =true;
    state.cartItems.push(boot);
  }
  export const SET_BOOTS = (state, boots) => {
    state.boots = boots;
  };
  export const REMOVE_BOOT_FROM_ORDER = (state, boot) => {
    state.order.orderItems =
      state.order.orderItems.filter((item) => {
        return item.boot.bootId !== boot.bootId;
      });
  };
  export const REMOVE_BOOT = (state, removeProduct) => {
    const bootId = removeProduct.bootId; // Accessing shirtId using reactive getter
    state.cartItems = state.cartItems.filter(item => item.bootId !== bootId);
  };
  export const INCREASE_BOOT_QUANTITY = (state, boot) => {
    const foundBoot = state.cartItems.find(item => item.bootId === boot.bootId);
    if (foundBoot.quantity < foundBoot.bootQuantity) {
      state.cartItems = state.cartItems.map(item => {
        if (item.bootId === boot.bootId) {
          item.quantity += 1;
          // item.shirtQuantity -= 1;
        }
        return item;
      });
    }
    else{
      swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Sorry, the stock is over for this item!",
      });
    }
  };
  
  export const DECREASE_BOOT_QUANTITY = (state, boot) => {
    const foundBoot = state.cartItems.find(item => item.bootId === boot.bootId);
    if (foundBoot && foundBoot.bootQuantity > 1 && foundBoot.quantity > 1 ) {
      state.cartItems = state.cartItems.map(item => {
        if (item.bootId === boot.bootId) {
          item.quantity -= 1;
        }
        return item;
      });
    }
  };
  //------------------------------------ for accessories----------------------------
  export const ADD_HAT = (state,hat) => {
    state.choose = true;
    state.cartItems.push(hat);
  };
  export const SET_HATS = (state, hats) => {
    state.hats = hats;
  };
  export const REMOVE_HAT_FROM_ORDER = (state, hat) => {
    state.order.orderItems =
      state.order.orderItems.filter((item) => {
        return item.hat.hatId !== hat.hatId;
      });
  };
  export const REMOVE_HAT = (state, removeProduct) => {
    const hatId = removeProduct.hatId; // Accessing shirtId using reactive getter
    state.cartItems = state.cartItems.filter(item => item.hatId !== hatId);
  };
  export const INCREASE_HAT_QUANTITY = (state, hat) => {
    const foundHat = state.cartItems.find(item => item.hatId === hat.hatId);
    if (foundHat.quantity < foundHat.hatQuantity) {
      state.cartItems = state.cartItems.map(item => {
        if (item.hatId === hat.hatId) {
          item.quantity += 1;
          // item.shirtQuantity -= 1;
        }
        return item;
      });
    }
    else{
      swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Sorry, the stock is over for this item!",
      });
    }
  };
  
  export const DECREASE_HAT_QUANTITY = (state, hat) => {
    const foundHat = state.cartItems.find(item => item.hatId === hat.hatId);
    if (foundHat && foundHat.hatQuantity > 1 && foundHat.quantity > 1 ) {
      state.cartItems = state.cartItems.map(item => {
        if (item.hatId === hat.hatId) {
          item.quantity -= 1;
        }
        return item;
      });
    }
  };
// ------------------------------------------------
export const SET_ORDER = (state, order) => {
  state.order = order;
};
export const SET_CUSTOMER = (state, customer) => {
  state.customer = customer;
};
export const CREATE_ORDER = (state) => {
  state.order = {
    orderItems: [...state.cartItems]
  };
};
export const CLEAR_CART =(state) =>{
  state.cartItems= [];
};
// export const ADD_TO_ORDER = (state,cartItems) =>{
//   state.order.orderItems.push(cartItems);
// }