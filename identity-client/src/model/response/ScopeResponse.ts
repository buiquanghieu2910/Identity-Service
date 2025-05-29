import { IdNameResponse, ResponseCommon } from '@/model/Common.ts'

export class ScopeResponse extends ResponseCommon {
  name: string = ''
  description: string = ''
  applicationId: string = ''
  resources: IdNameResponse[] = []
  permissions: IdNameResponse[] = []
}