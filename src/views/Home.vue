<template>
  <div>
    <Appbar />
    <v-container fluid class="image-container">
      <v-img
        :src="currentPhoto"
        class="mx-auto"
        max-width="100%"
        max-height="100vh"
        @click.left="changePhoto('left')"
        @click.right="changePhoto('right')"
      ></v-img>
    </v-container>
  </div>
</template>

<script>
import Appbar from '@/components/Appbar.vue';

export default {
  components: {
    Appbar,
  },
  data() {
    return {
      currentPhotoIndex: 0, 
      photos: [
        require('@/assets/photo0.jpg'),
        require('@/assets/photo1.jpg'),
        require('@/assets/photo2.jpg'),
        require('@/assets/photo3.jpg'),
        require('@/assets/photo4.jpg'),
        require('@/assets/photo5.jpg'),
        require('@/assets/photo6.jpg'),
        require('@/assets/photo7.jpg'),
      ],
      autoChangeInterval: null,
    };
  },
  computed: {
    currentPhoto() {
      return this.photos[this.currentPhotoIndex];
    },
  },
  mounted() {
    this.autoChangeInterval = setInterval(() => {
      this.changePhoto('right');
    }, 4000);
  },
  beforeDestroy() {
    clearInterval(this.autoChangeInterval);
  },
  methods: {
    changePhoto(direction) {
      if (direction === 'left') {
        this.currentPhotoIndex = (this.currentPhotoIndex - 1 + this.photos.length) % this.photos.length;
      } else if (direction === 'right') {
        this.currentPhotoIndex = (this.currentPhotoIndex + 1) % this.photos.length;
      }
    },
  },
};
</script>

<style scoped>
.image-container {
  height: 100vh; 
  overflow-y: hidden;
  margin-top: -50%; 
}
</style>
