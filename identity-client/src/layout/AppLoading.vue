<script lang="ts" setup>
import { visible } from '@/service/LoadingService'
</script>

<template>
  <div class="loader-mark" v-if="visible">
    <div class="loader">
      <div class="inner one"></div>
      <div class="inner two"></div>
      <div class="inner three"></div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.loader-mark {
  position: fixed;
  bottom: 0;
  left: 0;
  margin: 0;
  right: 0;
  top: 0;
  background-color: rgba(128, 128, 128, 0.4);
  z-index: 9999;
}

.loader {
  position: absolute;
  top: calc(50% - 32px);
  left: calc(50% - 32px);
  width: 64px;
  height: 64px;
  border-radius: 50%;
  perspective: 800px;
}

.inner {
  position: absolute;
  box-sizing: border-box;
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

.inner.one {
  left: 0%;
  top: 0%;
  animation: rotate-one 1s linear infinite;
  /* border-bottom: 3px solid #a12cff; */
  border-bottom: 3px solid var(--primary-color);
}

.inner.two {
  right: 0%;
  top: 0%;
  animation: rotate-two 1s linear infinite;
  /* border-right: 3px solid #ac46ff; */
  border-right: 3px solid var(--primary-color);
}

.inner.three {
  right: 0%;
  bottom: 0%;
  animation: rotate-three 1s linear infinite;
  /* border-top: 3px solid #ba64ff; */
  border-top: 3px solid var(--primary-color);
}

@keyframes rotate-one {
  0% {
    transform: rotateX(35deg) rotateY(-45deg) rotateZ(0deg);
  }
  100% {
    transform: rotateX(35deg) rotateY(-45deg) rotateZ(360deg);
  }
}

@keyframes rotate-two {
  0% {
    transform: rotateX(50deg) rotateY(10deg) rotateZ(0deg);
  }
  100% {
    transform: rotateX(50deg) rotateY(10deg) rotateZ(360deg);
  }
}

@keyframes rotate-three {
  0% {
    transform: rotateX(35deg) rotateY(55deg) rotateZ(0deg);
  }
  100% {
    transform: rotateX(35deg) rotateY(55deg) rotateZ(360deg);
  }
}
</style>