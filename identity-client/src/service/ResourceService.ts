import type { SearchParams } from '@/model/Common.ts'
import type { Response } from '@/model/response/Response.ts'
import apiClient from '@/service/Api.ts'
import { customParamSearch } from '@/utils/Util.ts'
import type { ResourceResponse } from '@/model/response/ResourceResponse.ts'

const URL = '/v1/resource'

export const fetchResources = async (paramSearch: SearchParams): Promise<Response<ResourceResponse[]>> => {
  const response =
    await apiClient.get<Response<ResourceResponse[]>>(URL, { params: customParamSearch(paramSearch) })
  return response.data
}

export const fetchResourceById = async (id: string): Promise<Response<ResourceResponse>> => {
  const response =
    await apiClient.get<Response<ResourceResponse>>(`${URL}/${id}`)
  return response.data
}