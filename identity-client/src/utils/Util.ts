import { PAGE_DEFAULT, PAGE_SIZE_DEFAULT } from '@/constants/Constant.ts'

export const customParamSearch = (param: any) => {
  const sorts = param?.sort ?? [];
  let sortCustoms: string[] = [];
  // @ts-ignore
  sorts.forEach((s) => {
    if (s.order) {
      sortCustoms.push(`${s.field}:${s.order}`);
    }
  });

  const searches = param?.search ?? [];
  let search = {};
  searches.forEach((s: { value: any; field: string | number }) => {
    if (s.value) {
      // @ts-ignore
      search[s.field] = s.value;
    }
  });
  return {
    page: param?.page || PAGE_DEFAULT,
    size: param?.size || PAGE_SIZE_DEFAULT,
    sort: sortCustoms.join(','),
    ...search,
  };
}