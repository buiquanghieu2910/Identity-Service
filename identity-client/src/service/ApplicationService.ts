import apiClient from '@/service/Api.ts'
import type { ApplicationResponse } from '@/model/response/ApplicationResponse.ts'
import type { Response } from '@/model/response/Response.ts'
import type { SearchParams } from '@/model/Common.ts'
import { customParamSearch } from '@/utils/Util.ts'

const URL = '/v1/application';

export const fetchApplications = async (paramSearch: SearchParams): Promise<Response<ApplicationResponse[]>> => {
  const response =
    await apiClient.get<Response<ApplicationResponse[]>>(URL, { params: customParamSearch(paramSearch) })
  return response.data
}

export const fetchApplicationById = async (id: string): Promise<Response<ApplicationResponse>> => {
  const response =
    await apiClient.get<Response<ApplicationResponse>>(`${URL}/${id}`)
  return response.data
}