import { ResponseCommon } from '@/model/Common.ts'

export class ResourceResponse extends ResponseCommon{
  name: string = '';
  description: string = '';
  applicationId: string = '';
  uris: string[] = [];
  scopeIds: string[] = [];
}