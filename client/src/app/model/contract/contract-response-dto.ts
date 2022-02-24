import { ResponseDto } from "../response-dto";
import {DepartmentResponseDto} from "../department/department-response-dto";
import {DealerResponseDto} from "../dealer/dealer-response-dto";

export interface ContractResponseDto extends ResponseDto {

  name: string;
  contractType: string;
  department: DepartmentResponseDto;
  dealer: DealerResponseDto;
}
