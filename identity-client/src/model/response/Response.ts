export interface Response<T> {
  meta?: MetaData;
  data?: T;
}

export interface MetaData {
  code?: string;
  page?: number;
  size?: number;
  total?: number;
  totalPages?: number;
  message?: string;
  error?: object;
}

export interface PaginatorData {
  page: number;
  size: number;
  total: number;
  totalPages: number;
}