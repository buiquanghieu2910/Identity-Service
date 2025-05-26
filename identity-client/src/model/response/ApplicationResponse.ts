import { Status } from '@/model/enumable/Status.ts'

export class ApplicationResponse {
  id: string = ''
  name: string = ''
  clientId: string = ''
  clientSecret: string = ''
  redirectUris: string[] = []
  webOrigins: string[] = []
  description: string = ''
  createdAt?: Date
  createdBy: string = ''
  updatedAt?: Date
  updatedBy: string = ''
  status: Status = Status.IN_ACTIVE
  statusCheckbox: boolean = false

  constructor(data?: Partial<ApplicationResponse>) {
    if (data) {
      Object.assign(this, data)
      this.redirectUris = data.redirectUris ? [...data.redirectUris] : []
      this.webOrigins = data.webOrigins ? [...data.webOrigins] : []
      this.statusCheckbox = this.status === Status.ACTIVE
    }
  }
}
