import { IdNameResponse } from '@/model/Common.ts'
import type { Response } from '@/model/response/Response.ts'
import apiClient from '@/service/Api.ts'

const URL = '/v1/role'

export const fetchIdNamesByApplicationId = async (applicationId: string): Promise<Response<IdNameResponse[]>> => {
  const response =
    await apiClient.get<Response<IdNameResponse[]>>(`${URL}/by-application-id/${applicationId}/id-names`)
  return response.data
}