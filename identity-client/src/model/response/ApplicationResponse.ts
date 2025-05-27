import { Status } from '@/model/enumable/Status.ts'
import { ResponseCommon } from '@/model/Common.ts'

export class ApplicationResponse extends ResponseCommon{
  name: string = ''
  clientId: string = ''
  clientSecret: string = ''
  redirectUris: string[] = []
  webOrigins: string[] = []
  description: string = ''
  status: Status = Status.IN_ACTIVE
  statusCheckbox: boolean = false

  constructor(data?: Partial<ApplicationResponse>) {
    super()
    if (data) {
      Object.assign(this, data)
      this.redirectUris = data.redirectUris ? [...data.redirectUris] : []
      this.webOrigins = data.webOrigins ? [...data.webOrigins] : []
      this.statusCheckbox = this.status === Status.ACTIVE
    }
  }
}
