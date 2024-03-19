<template>
    <div>
      <AdminSidebar/>
      <v-data-table
        :headers="headers"
        :items="hatList"
        :items-per-page="10"
        class="elevation-1"
      >
        <!-- Poster Img -->
        <template v-slot:[`item.hatPhoto`]="{ item }">
          <v-img
            :src="localDomain + item.hatPhoto"
            width="100"
            height="100"
            contain
          ></v-img>
        </template>
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title>ACCESSORIES</v-toolbar-title>
            <v-divider class="mx-4" inset vertical></v-divider>
  
            <!-- -------------****** create dialog ******-------------------------- -->
            <v-dialog v-model="dialog" max-width="450px">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  color="primary"
                  class="mb-2 widthoutupercase white--text"
                  v-bind="attrs"
                  v-on="on"
                >
                  Add Accessories
                </v-btn>
              </template>
              <template
                ><v-form ref="createHatForm" v-model="createHatForm">
                  <v-card>
                    <v-card-title>
                      <h3>Add New Accessories</h3>
                    </v-card-title>
                    <v-card-text>
                      <v-container>
                        <v-row>
                          <!-- Hat type -->
                          <v-col cols="12" sm="6" md="6">
                            <v-text-field
                              v-model="createDemo.hatType"
                              :rules="[
                                (v) => !!v || 'Required',
                                (v) =>
                                  (v && v.length <= 50) ||
                                  'Title must be less than 50 characters',
                              ]"
                              label="Accessories Name"
                            ></v-text-field>
                          </v-col>
                          <!-- Category -->
                     <v-col class="d-flex" cols="12" sm="6">
                       <v-select
                         v-model="createDemo.hatCategory"
                         :items="categoryList"
                         item-text="categoryName"
                         item-value="categoryId"
                         :rules="[(v) => !!v || 'Required']"
                         label="Category"
                         required
                       >
                       </v-select>
                     </v-col> 
                    </v-row>

                    <v-row>
                      <!-- hat Quatntity -->
                      <v-col class="d-flex" cols="12" sm="6">
                       <v-text-field
                         v-model="createDemo.hatQuantity"
                         type="number"
                         min="1"
                         :rules="[
                           (v) => !!v || 'Required',
                           (v) =>
                           (v && v > 0) ||
                           'Quantity must be greater than 0',
                         ]"
                         label="Quantity"
                         required
                       ></v-text-field>
                     </v-col>
                     <!-- hat price -->
                      
                     <v-col class="d-flex" cols="12" sm="6">
                            <v-text-field
                              v-model="createDemo.hatPrice"
                              type="number"
                              suffix="MMK"
                              min="1"
                              :rules="[
                                (v) => !!v || 'Required',
                                (v) =>
                                (v && v > 0) ||
                                'Price must be greater than 0 MMK',
                              ]"
                              label="Price"
                              required
                            ></v-text-field>
                          </v-col>
                    </v-row>
                        <v-row>
                          <v-col class="d-flex" cols="12" sm="12">
                            <!-- Poster -->
                            <v-file-input
                              v-model="poster"
                              label="Photo"
                              show-size
                              prepend-icon="mdi-camera"
                              placeholder="Choose Poster Image"
                              accept="image/png, image/jpeg"
                              :rules="[
                                (v) => !!v || 'Required',
                                (v) =>
                                  !v ||
                                  v.size < 10000000 ||
                                  'Image size should be less than 10 MB!',
                              ]"
                              @change="onChangePoster"
                            ></v-file-input>
                          </v-col>
                          <!-- Poster Preview -->
                          <v-img
                            v-if="posterPreviewPath != null"
                            :src="posterPreviewPath"
                            width="200"
                            height="150"
                            contain
                          >
                          </v-img
                        ></v-row>
                      </v-container>
                    </v-card-text>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn
                        class="mb-2 widthoutupercase"
                        text
                        @click="saveCancel"
                        >Cancel
                      </v-btn>
                      <v-btn
                        color="primary"
                        class="mb-2 widthoutupercase white--text"
                        @click="save"
                        :disabled="!createHatForm"
                        ><span v-if="!loading">Save</span>
                        <v-progress-circular
                          v-else
                          indeterminate
                          color="primary"
                        ></v-progress-circular
                      ></v-btn>
                    </v-card-actions>
                    <v-alert class="mt-3" v-show="errorAlert" dense type="error">
                      Create Accessories Failed!
                    </v-alert>
                  </v-card>
                  <!-- Error Alert For Category -->
                </v-form></template
              >
            </v-dialog>
          </v-toolbar>
        </template>
        <template v-slot:[`item.hatType`]="{ item }">
          {{ item.hatType | capitalising }}
        </template>
        <template v-slot:[`item.category`]="{ item }">
          <h3 class="primary--text">
            {{ item.hatCategory.categoryName }}
          </h3>
        </template>
        <template v-slot:[`item.createdAt`]="{ item }">
          {{ item.createdAt | formatLocalDate }}
        </template>
        <template v-slot:[`item.updatedAt`]="{ item }">
          {{ item.updatedAt | formatLocalDate }}
        </template>
        <template v-slot:[`item.actions`]="{ item }">
          <v-icon color="indigo" dark class="mr-2" @click="editItem(item)">
            mdi-pencil
          </v-icon>
          <v-icon color="error" dark @click="deleteItem(item)">
            mdi-delete
          </v-icon>
        </template>
      </v-data-table>
      <v-toolbar>
        <!-- -------------****** update dialog ******-------------------------- -->
        <v-form ref="createHatForm" v-model="createHatForm">
          <v-dialog v-model="update_dialog" max-width="450px">
            <v-card>
              <v-card-title>
                <h3>Edit Accessories</h3>
              </v-card-title>
              <v-card-text>
                <v-container>
                    <!-- hat name -->
                  <v-row>
                    <v-col cols="12" sm="6" md="6">
                      <v-text-field
                        v-model="updateDemo.hatType"
                        :rules="[
                          (v) => !!v || 'Required',
                          (v) =>
                            (v && v.length <= 50) ||
                            'Title must be less than 50 characters',
                        ]"
                        label="Accessories Name"
                      ></v-text-field>
                    </v-col>
                    <!-- Category -->
                  <v-col class="d-flex" cols="12" sm="6">
                       <v-select
                         v-model="updateDemo.hatCategory.categoryId"
                         :items="categoryList"
                         item-text="categoryName"
                         item-value="categoryId"
                         :rules="[(v) => !!v || 'Required']"
                         label="Category"
                         required
                       >
                       </v-select>
                     </v-col>
                  </v-row>
  
                  <v-row>
                     <!-- hat price -->
                    <v-col class="d-flex" cols="12" sm="6">
                      <v-text-field
                        v-model="updateDemo.hatPrice"
                        type="number"
                        suffix="MMK"
                        min="1"
                        :rules="[
                          (v) => !!v || 'Required',
                          (v) =>
                            (v && v > 0) || 'Price must be greater than 0 MMK',
                        ]"
                        label="Price"
                        required
                      ></v-text-field>
                    </v-col>
                    <!-- hat quantity -->
                    <v-col class="d-flex" cols="12" sm="6">
                      <v-text-field
                        v-model="updateDemo.hatQuantity"
                        type="number"
                        suffix="MMK"
                        min="1"
                        :rules="[
                          (v) => !!v || 'Required',
                          (v) =>
                            (v && v > 0) || 'Quantity must be greater than 0 MMK',
                        ]"
                        label="Quantity"
                        required
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <!-- -------------****** poster start ******-------------------------- -->
                  <v-row>
                    <v-col cols="12" sm="12">
                      <v-file-input
                        v-model="updateDemo.poster"
                        label="Photo"
                        show-size
                        prepend-icon="mdi-camera"
                        placeholder="Choose Poster Image"
                        accept="image/png, image/jpeg"
                        :rules="[
                          (v) => !!v || 'Required',
                          (v) =>
                            !v ||
                            v.size < 10000000 ||
                            'Image size should be less than 10 MB!',
                        ]"
                        @change="onChangePoster"
                      ></v-file-input>
                    </v-col>
                  </v-row>
                  <!-- -------------****** Photo Preview start ******-------------------------- -->
                  <v-row>
                    <!-- Poster is not selected (null) -->
                    <v-img
                      v-if="posterPreviewPath == null"
                      :src="localDomain + updateDemo.hatPhoto"
                      width="200"
                      height="150"
                      contain
                    >
                    </v-img>
                    <!-- Poster is selected (not null) -->
                    <v-img
                      v-if="posterPreviewPath != null"
                      :src="posterPreviewPath"
                      width="200"
                      height="150"
                      contain
                    >
                    </v-img>
                  </v-row>
                  <!-- -------------****** Photo Preview end ******-------------------------- -->
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn class="mb-2 widthoutupercase" text @click="closeUpdate"
                  >Cancel</v-btn
                >
                <v-btn
                  color="primary"
                  class="mb-2 widthoutupercase white--text"
                  @click="updateItemConfirm(updateDemo.hatId)"
                  >Update</v-btn
                >
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-form>
        <!-- -------------****** delete ******-------------------------- -->
        <v-container>
          <v-dialog v-model="delete_dialog" max-width="400px">
            <v-card>
              <v-card-title
                ><span>Are you sure you want to delete this?</span></v-card-title
              >
  
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn class="mb-2 widthoutupercase" text @click="closeDelete"
                  >Cancel</v-btn
                >
                <v-btn
                  color="primary"
                  class="mb-2 widthoutupercase white--text"
                  @click="deleteItemConfirm(deleteDemo.hatId)"
                  >Delete</v-btn
                >
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-container>
      </v-toolbar>
    </div>
  </template>
  
  <script>
  import swal from "sweetalert2";
  window.swal = swal;
  import utils from "@/utils/utils";
  import AdminSidebar from '@/components/AdminSidebar.vue';
  export default {
    components: { 
      AdminSidebar,
     },
     filters: {
          capitalising: function (value) {
              if (!value) return '';
              value = value.toString();
              return value.charAt(0).toUpperCase() + value.slice(1);
          }
      },
    data:() =>({
      localDomain: utils.constant.localDomain,
      createHatForm: false,
      dialog: false,
      update_dialog: false,
      delete_dialog: false,
      loading: false,
      errorAlert: false,
      poster: null,
      posterPreviewPath: null,
      deleteDemo: {},
      updateDemo: {
        hatType: "",
        hatCategory:""
      },
      createDemo: {
        hatType:"",
        hatPrice:"",
        hatQuantity:"",
        hatPhoto:null,
        hatCategory:1,
      },
      categoryList:[],
      hatList: [],
      headers: [
        { text: "Photo", value: "hatPhoto" },
        { text: "Name", value: "hatType" },
        { text: "Price(Ks)", value: "hatPrice" },
        { text: "Quanttity", value: "hatQuantity" },
        { text: "Category", value: "category" },
        { text: "Created Date", value: "createdAt" },
        { text: "Modified Date", value: "updatedAt" },
        { text: "Actions", value: "actions", sortable: false },
      ],
    }),
  
    async created() {
      await this.fetchAllCategories();
      await this.fetchAllHats();
    },
  
    methods: {
      // <!-- -------------****** get all hat from utils.http ******-------------------------- -->
      async fetchAllHats() {
        const response = await utils.http.get("/hat/list");
        if (response) {
          const data = await response.json();
          if (data) this.hatList = data;
        }
      },
       // <!-- -------------****** get all category from utils.http ******-------------------------- -->
       async fetchAllCategories() {
        const response = await utils.http.get("/category/list");
        if (response) {
          const data = await response.json();
          if (data) this.categoryList = data;
        }
      },
       // <!-- -------------****** save Category from utils.http ******-------------------------- -->
  
       async save() {
        if (this.$refs.createHatForm.validate()) {
          this.errorAlert = false;
          let HatTypeCheckOK = true;
  
          if (HatTypeCheckOK) {
            const respPoster = await utils.http.postMedia(
              "/admin/file/create",
              this.poster,
              this.poster.type
            );
            let respPosterData = null;
  
            if (respPoster.status === 200) {
              respPosterData = await respPoster.text();
              //console.log(this.respPosterData);
            } else {
              this.errorAlert = true;
            }
            if (respPosterData) {
              // Create hat utils.http
              const resp = await utils.http.post("/admin/hat/create", {
                hatType: this.createDemo.hatType,
                hatPrice: this.createDemo.hatPrice,
                hatQuantity: this.createDemo.hatQuantity,
                hatCategory: { categoryId: this.createDemo.hatCategory },
                hatPhoto: respPosterData,
              });
  
              if (resp && resp.status === 200) {
                await this.fetchAllHats();
                this.dialog = false;
                this.createDemo = {};
                this.poster = null;
                this.posterPreviewPath = null;
              }
              else {
              swal.fire({
                icon: "error",
                title: "Sorry...",
                text: "Shirt cannot created!",
              });
            }
           }
            this.loading = false;
          }
        }
      },
  
      // <!-- -------------****** update table from utils.http ******-------------------------- -->
      async updateItemConfirm(id) {
        this.errorAlert = false;
        this.loading = true;
        let hatPhoto = this.updateDemo.hatPhoto;
        // Step 1 -> Update Poster
        // Null -> Poster is not selected
        if (this.updateDemo.poster != null) {
          // Update Poster utils.http
          const respPosterData = await utils.http.putMedia(
            "/admin/file/update",
            this.updateDemo.poster,
            this.updateDemo.poster.type,
            this.updateDemo.hatPhoto
          );
  
          if (respPosterData && respPosterData.status === 200) {
           hatPhoto = await respPosterData.text();
          } else {
            console.debug("Poster Update Failed");
          }
        }
        // console.log("this is",this.updateDemo)
        const resp = await utils.http.put("/admin/hat/update/" + id, {
                hatType: this.updateDemo.hatType,
                hatPrice: this.updateDemo.hatPrice,
                hatCategory: {
                    categoryId: this.updateDemo.hatCategory.categoryId
                  },
                hatQuantity: this.updateDemo.hatQuantity,
                hatPhoto: hatPhoto,
        });

        if (resp && resp.status === 200) {
          this.update_dialog = false;
          await this.fetchAllHats();
        } else {
          swal.fire({
            icon: "error",
            title: "Sorry...",
            text: "Hat cannot update!",
          });
        }
  
        this.loading = false;
        
      },
      // <!-- -------------****** delete table from utils.http ******-------------------------- -->
      async deleteItemConfirm(id) {
        const resp = await utils.http.del("/admin/hat/delete/" + id);
        if (resp.status == 200) {
          await this.fetchAllHats();
          this.delete_dialog = false;
        }
      },
      // <!-- -------------******  event handelar from dialog ******-------------------------- -->
      onChangePoster(poster) {
        this.posterPreviewPath = URL.createObjectURL(poster);
      },
      deleteItem(item) {
        this.delete_dialog = true;
        this.deleteDemo = item;
      },
      editItem(item) {
        this.update_dialog = true;
        // Copy Object
        this.updateDemo = Object.assign({}, item);
        this.hatType = this.updateDemo.hatType;
        this.hatPrice = this.updateDemo.hatPrice;
        this.hatCategory = {
            categoryId: this.updateDemo.hatCategory.categoryId };
        this.hatQuantity=this.updateDemo.hatQuantity;
        this.hatPhoto = this.updateDemo.hatPhoto;
        this.poster = this.updateDemo.poster;
        this.posterPreviewPath = this.updateDemo.posterPreviewPath;
      },
      closeUpdate() {
        this.update_dialog = false;
      },
      closeDelete() {
        this.delete_dialog = false;
      },
      saveCancel() {
        this.dialog = false;
        this.createDemo = {};
        this.poster = null;
        this.posterPreviewPath = null;
      },
      
    },
  };
  </script>
  <style>
  .v-btn:hover {
    background-color: brown;
    color: white !important;
  }
  </style>
  
  
  