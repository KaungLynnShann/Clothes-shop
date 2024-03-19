export const loginUser = (state) => {
    let loginUser = sessionStorage.getItem("loginUser");
    if (loginUser != null) {
      state.loginUser = JSON.parse(loginUser);
      return JSON.parse(loginUser);
    } else {
      return state.loginUser;
    }
  };
  export const isLogin = (state) => {
    let isLogin = sessionStorage.getItem("isLogin");
    if (isLogin != null) {
      state.isLogin = isLogin;
      return isLogin;
    } else {
      return state.isLogin;
    }
  };
  export const getCartLength = (state) => {
    const totalItemsInCart = state.cartItems.length 
    return totalItemsInCart;
  };
  
  export const getOrderHistory = state => state.orderHistory;
