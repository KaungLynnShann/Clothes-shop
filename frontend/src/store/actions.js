import * as types from './mutations';
import axios from "axios";

const SHIRT_LIST = "http://localhost:8081/shirt/list";
const PANTS_LIST= "http://localhost:8081/pants/list";
const BOOT_LIST = "http://localhost:8081/boot/list";
const HAT_LIST = "http://localhost:8081/hat/list";

export const actions = {

// ------------------------for shirt------------------------------
  addShirt({ commit }, shirt) {
    commit(types.ADD_SHIRT, shirt);
  },
  getAllShirts({ commit })  {
    axios.get(SHIRT_LIST).then((response) => {
      commit("SET_SHIRTS", response.data);
    });
  },
 removeShirtFromOrder  ({ commit },shirt)  {
    commit("REMOVE_SHIRT_FROM_ORDER", shirt);
  },
  removeShirt({ commit }, removeProduct) {
    commit('REMOVE_SHIRT', removeProduct);
  },
  increaseShirtQuantity({commit}, shirt) {
    setTimeout(()=>{
     commit('INCREASE_SHIRT_QUANTITY',shirt);
      },100);
  },
  decreaseShirtQuantity({commit}, shirt) {
    setTimeout(()=>{
      commit('DECREASE_SHIRT_QUANTITY',shirt);
      },100);
  },
  // -------------------------for pants--------------------------------
  addPants({ commit }, pants) {
    commit(types.ADD_PANTS, pants);
  },
  getAllPants({ commit })  {
    axios.get(PANTS_LIST).then((response) => {
      commit("SET_PANTS", response.data);
    });
  },
  removePantsFromOrder  ({ commit },pants)  {
    commit("REMOVE_PANTS_FROM_ORDER", pants);
  },
  removePants({ commit }, removeProduct) {
    commit('REMOVE_PANTS', removeProduct);
  },
  increasePantsQuantity({commit}, pants) {
    setTimeout(()=>{
     commit('INCREASE_PANTS_QUANTITY',pants);
      },100);
  },
  decreasePantsQuantity({commit}, pants) {
    setTimeout(()=>{
      commit('DECREASE_PANTS_QUANTITY',pants);
      },100);
  },
  // -------------------------for boot-----------------------------------
  addBoot({ commit }, boot) {
    commit(types.ADD_BOOT, boot);
  },
  getAllBoots({ commit })  {
    axios.get(BOOT_LIST).then((response) => {
      commit("SET_BOOTS", response.data);
    });
  },
  removeBootFromOrder  ({ commit },boot)  {
    commit("REMOVE_BOOT_FROM_ORDER", boot);
  },
  removeBoot({ commit }, removeProduct) {
    commit('REMOVE_BOOT', removeProduct);
  },
  increaseBootQuantity({commit}, boot) {
    setTimeout(()=>{
     commit('INCREASE_BOOT_QUANTITY',boot);
      },100);
  },
  decreaseBootQuantity({commit}, boot) {
    setTimeout(()=>{
      commit('DECREASE_BOOT_QUANTITY',boot);
      },100);
  },
  // -------------------------for accessories-----------------------------------
  addHat({ commit }, hat) {
    commit(types.ADD_HAT, hat);
  },
  getAllHats({ commit })  {
    axios.get(HAT_LIST).then((response) => {
      commit("SET_HATS", response.data);
    });
  },
  removeHatFromOrder  ({ commit },hat)  {
    commit("REMOVE_HAT_FROM_ORDER", hat);
  },
  removeHat({ commit }, removeProduct) {
    commit('REMOVE_HAT', removeProduct);
  },
  increaseHatQuantity({commit}, hat) {
    setTimeout(()=>{
     commit('INCREASE_HAT_QUANTITY',hat);
      },100);
  },
  decreaseHatQuantity({commit}, hat) {
    setTimeout(()=>{
      commit('DECREASE_HAT_QUANTITY',hat);
      },100);
  },
  // ---------------------------------------
   clearCart({ commit }){
    commit("CLEAR_CART");
  },
  async createOrder({ commit }, orderData) {
    try {
      const response = await axios.post('http://localhost:8081/order/create', orderData);
      if (response && response.status === 200) {
        const createdOrder = response.data;
        commit('SET_ORDER', createdOrder);
        return createdOrder;
      }
    } catch (error) {
      console.error('An error occurred while creating the order:', error);
      throw error;
    }
  }
};
