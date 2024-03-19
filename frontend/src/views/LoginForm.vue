<template>
  <v-container class="center">
    <v-sheet width="500" class="form-container">
      <v-form ref="loginForm" v-model="loginForm"  @submit.prevent="login" >
        <v-row>
          <v-col class="close-button">
            <v-btn icon @click="goBack">
              <v-icon color="red">mdi-close</v-icon>
            </v-btn>
          </v-col>
        </v-row>

        <v-text-field 
        v-model="userEmail" 
        label="Email" :rules="[
          (v) => !!v || 'Required',
          (v) =>
            /.+@.+\..+/.test(v) || 'E-mail must be valid',
        ]"
         prepend-icon="email" 
         type="text"
         required />
         <!-- <v-text-field v-model="userEmail" label="Email"/> -->
         <v-text-field
          v-model="userPassword"
          :append-icon="showPassword ? 'mdi-eye-off' : 'mdi-eye'"
          @click:append="showPassword = !showPassword"
          :type="showPassword ? 'text' : 'password'"
          label="Password"
          :counter="10"
          :rules="[
            (v) => !!v || 'Required',
            (v) =>
              (v && v.length <= 10) ||
              'Password must be less than 10 characters',
          ]"
          prepend-icon="lock"
          required
        />
         <!-- -------------------button -------------------->
        <div class="text-center mt-3">
        <v-btn 
          type="submit"
          class="btn"
          dark
        :disabled="!loginForm"
      >
        <span v-if="!loading">LOGIN</span>
        <v-progress-circular
          v-else
          indeterminate
          color="white"
        ></v-progress-circular>
        </v-btn>
        <v-alert
           class="mt-3"
          v-show="errorAlert"
          dense
          type="error">
          Login Failed! <br />
          Email or Password is wrong!
        </v-alert>
      </div>
      </v-form>
    </v-sheet>
  </v-container>
</template>
  
<script>
import utils from '@/utils/utils';
export default {
  name: "LoginForm",
  props: {
    source: String,
  },
  data() {
    return {
      step: 1,
      loginForm: false,
      errorAlert: false,
      loading: false,
      userEmail: '',
      userPassword: '',
      showPassword: false,
    };
  },
  async created() {},
  methods: {
    async login() {
      if (this.$refs.loginForm.validate()) {
        this.errorAlert = false;
        this.loading = true;
        const resp = await utils.http.post("/user/login", {
          // userName:this.userName,
          userEmail: this.userEmail,
          userPassword: this.userPassword,
        });
        if (resp.status === 200) {
          const data = await resp.json();
          if (data) {
            this.$store.commit("setLoginUser", data);
            // console.log("successful");
            if (data.userEmail === "admin@gmail.com" || data.userPassword === "admin") {
              this.$router.push({ path: "/admin/category" });
            } else {
              this.$router.push({ path: "/" });
            }
          }
        } else {
          this.errorAlert = true;
        }
        this.loading = false;
      }
    },
    goBack() {
      this.$router.push("/");
    },
  },
};
</script>
  
<style scoped>
.center {
  display: flex;
  justify-content: center;
  height: 100vh;
}

.form-container {
  border: 1px solid #ccc;
  padding: 20px;
  border-radius: 10px;
  margin-bottom: auto;
}

.btn {
  background-color: blue !important;
  color: white !important;
}
.close-button {
  text-align: right;
}
.close-button:hover .v-btn{
  background-color: transparent !important;
}
</style>
  