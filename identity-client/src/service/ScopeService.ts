import { IdNameResponse } from '@/model/Common.ts'
import type { Response } from '@/model/response/Response.ts'
import apiClient from '@/service/Api.ts'
import { ScopeResponse } from '@/model/response/ScopeResponse.ts'

const URL = '/v1/scope'

export const fetchScopesByApplicationId = async (applicationId: string): Promise<Response<ScopeResponse[]>> => {
  const response =
    await apiClient.get<Response<ScopeResponse[]>>(`${URL}/by-application-id/${applicationId}`)
  return response.data
}

export const fetchScopeById = async (scopeId: string): Promise<Response<ScopeResponse>> => {
  const response =
    await apiClient.get<Response<ScopeResponse>>(`${URL}/${scopeId}`)
  return response.data
}

export const fetchIdNamesByApplicationId = async (applicationId: string): Promise<Response<IdNameResponse[]>> => {
  const response =
    await apiClient.get<Response<IdNameResponse[]>>(`${URL}/by-application-id/${applicationId}/id-names`)
  return response.data
}

export const fetchIdNamesByResourceId = async (resourceId: string): Promise<Response<IdNameResponse[]>> => {
  const response =
    await apiClient.get<Response<IdNameResponse[]>>(`${URL}/by-resource-id/${resourceId}/id-names`)
  return response.data
}