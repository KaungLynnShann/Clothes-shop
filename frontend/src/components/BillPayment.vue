<template>
  <v-container>
    <h3 class="ml-3">Bills Payments</h3>
    <!-- Shirt List -->
    <v-list
      subheader
      two-line
      class="mt-1"
      v-for="shirt in shirtItems"
      :key="'shirt-'+shirt.shirtId"
    >
      <v-list-item>
        <v-list-item-avatar rounded color="grey lighten-4">
          <v-img :src="localDomain + shirt.shirtPhoto"></v-img>
        </v-list-item-avatar>
        <v-list-item-content>
          <v-list-item-title class="subtitle-2">
            {{ shirt.shirtType }}
          </v-list-item-title>
          <v-list-item-subtitle>
            {{ shirt.quantity }} X
            <strong>{{ shirt.shirtPrice }} Ks</strong>
          </v-list-item-subtitle>
        </v-list-item-content>
        <v-list-item-action class="caption">
          {{ shirt.quantity * shirt.shirtPrice }}Ks
        </v-list-item-action>
      </v-list-item>
    </v-list>
    <!-- Pants List -->
    <v-list
      subheader
      two-line
      class="mt-1"
      v-for="pants in pantsItems"
      :key="'pants-'+ pants.pantsId"
    >
      <v-list-item>
        <v-list-item-avatar rounded color="grey lighten-4">
          <v-img :src="localDomain + pants.pantsPhoto"></v-img>
        </v-list-item-avatar>
        <v-list-item-content>
          <v-list-item-title class="subtitle-2">
            {{ pants.pantsType }}
          </v-list-item-title>
          <v-list-item-subtitle>
            {{ pants.quantity }} X
            <strong>{{ pants.pantsPrice }} Ks</strong>
          </v-list-item-subtitle>
        </v-list-item-content>
        <v-list-item-action class="caption">
          {{ pants.quantity * pants.pantsPrice }}Ks
        </v-list-item-action>
      </v-list-item>
    </v-list>
    <!-- Boot List -->
    <v-list
      subheader
      two-line
      class="mt-1"
      v-for="boot in bootItems"
      :key="'boot-'+boot.bootId"
    >
      <v-list-item>
        <v-list-item-avatar rounded color="grey lighten-4">
          <v-img :src="localDomain + boot.bootPhoto"></v-img>
        </v-list-item-avatar>
        <v-list-item-content>
          <v-list-item-title class="subtitle-2">
            {{ boot.bootType }}
          </v-list-item-title>
          <v-list-item-subtitle>
            {{ boot.quantity }} X
            <strong>{{ boot.bootPrice }} Ks</strong>
          </v-list-item-subtitle>
        </v-list-item-content>
        <v-list-item-action class="caption">
          {{ boot.quantity * boot.bootPrice }}Ks
        </v-list-item-action>
      </v-list-item>
    </v-list>
    <!-- Hat List -->
    <v-list
      subheader
      two-line
      class="mt-1"
      v-for="hat in hatItems"
      :key="'hat-'+hat.hatId"
    >
      <v-list-item>
        <v-list-item-avatar rounded color="grey lighten-4">
          <v-img :src="localDomain + hat.hatPhoto"></v-img>
        </v-list-item-avatar>
        <v-list-item-content>
          <v-list-item-title class="subtitle-2">
            {{ hat.hatType }}
          </v-list-item-title>
          <v-list-item-subtitle>
            {{ hat.quantity }} X
            <strong>{{ hat.hatPrice }} Ks</strong>
          </v-list-item-subtitle>
        </v-list-item-content>
        <v-list-item-action class="caption">
          {{ hat.quantity * hat.hatPrice }}Ks
        </v-list-item-action>
      </v-list-item>
    </v-list>

    <!-- Total -->
    <v-divider class="mx-4"></v-divider>
    <v-toolbar  flat>
      <strong>Total</strong><v-spacer></v-spacer>
      <strong>{{ calculated }}Ks</strong>
    </v-toolbar>

      <v-btn
        :disabled="disableOrderButton"
        class="primary"
        @click="$router.push('/cash')"
      >Order</v-btn>

  </v-container>
</template>

<script>
import { mapState } from "vuex";
import utils from "@/utils/utils";

export default {
  props: {
    disableOrderButton: {
      type: Boolean,
      default: false
    }
  },
  data: () => ({
    localDomain: utils.constant.localDomain,
  }),

  computed: {
    ...mapState(['cartItems']),
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
    calculated() {
      const shirtTotal = this.shirtItems.reduce((total, shirt) =>
        total + (shirt.quantity * shirt.shirtPrice), 0);
      const pantsTotal = this.pantsItems.reduce((total, pants) =>
        total + (pants.quantity * pants.pantsPrice), 0);
      const bootTotal = this.bootItems.reduce((total, boot) =>
        total + (boot.quantity * boot.bootPrice), 0);
      const hatTotal = this.hatItems.reduce((total, hat) =>
        total + (hat.quantity * hat.hatPrice), 0);
      return shirtTotal + pantsTotal + bootTotal + hatTotal;
    }
  },

  methods: { }
};
</script>

<style scoped>
/* Styles */
</style>
