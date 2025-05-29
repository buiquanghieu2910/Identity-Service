import type { SearchParams } from '@/model/Common.ts'
import type { Response } from '@/model/response/Response.ts'
import apiClient from '@/service/Api.ts'
import { customParamSearch } from '@/utils/Util.ts'
import { PermissionResponse } from '@/model/response/PermissionResponse.ts'

const URL = '/v1/permission'

export const fetchPermissions = async (paramSearch: SearchParams): Promise<Response<PermissionResponse[]>> => {
  const response =
    await apiClient.get<Response<PermissionResponse[]>>(URL, { params: customParamSearch(paramSearch) })
  return response.data
}

export const fetchPermissionById = async (id: string): Promise<Response<PermissionResponse>> => {
  const response =
    await apiClient.get<Response<PermissionResponse>>(`${URL}/${id}`)
  return response.data
}