import { ResponseCommon } from '@/model/Common.ts'

export class PermissionResponse extends ResponseCommon {
  name: string = ''
  description: string = ''
  applicationId: string = ''
  resourceId: string = ''
  scopeIds: string[] = []
  roleIds: string[] = []
}