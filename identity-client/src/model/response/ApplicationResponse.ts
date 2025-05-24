import { Status } from '@/model/enumable/Status.ts'

export interface ApplicationResponse {
  id: string;
  name: string;
  clientId: string;
  clientSecret: string;
  redirectUris: string[];
  webOrigins: string[];
  description: string;
  createdAt: Date;
  createdBy: string;
  updatedAt: Date;
  updatedBy: string;
  status: Status;
}