export interface SearchParams {
  page?: number;
  size?: number;
  search: ItemSearch[];
  sort: ItemSort[];
}

export interface ItemSearch {
  field?: string;
  value?: any;
}

export interface ItemSort {
  field?: string;
  order?: string;
}

export interface PaginatorData {
  page: number;
  size: number;
  total: number;
  totalPages: number;
}

export class ResponseCommon {
  id: string = ''
  createdAt?: Date
  createdBy: string = ''
  updatedAt?: Date
  updatedBy: string = ''
}

export class IdNameResponse {
  id: string = '';
  name: string = '';
}