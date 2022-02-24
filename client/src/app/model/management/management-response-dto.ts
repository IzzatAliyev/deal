import { ResponseDto } from "../response-dto";
import {DepartmentShortResponseDto} from "../department/department-short-response-dto";

export interface ManagementResponseDto extends ResponseDto {

  name: string;
  departments: DepartmentShortResponseDto[];
}
