// loading.ts
import { readonly, ref } from 'vue'

const _visible = ref(false);
export const visible = readonly(_visible);

export const showLoading = () => {
  _visible.value = true;
};

export const hideLoading = () => {
  _visible.value = false;
};
