import { ResponseDto } from "../response-dto";
import {ManagementShortResponseDto} from "../management/management-short-response-dto";

export interface DepartmentResponseDto extends ResponseDto {
  name: string;
  departmentType: string;
  managements: ManagementShortResponseDto[];
}
