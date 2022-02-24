import { ResponseDto } from "../response-dto";

export interface DealerResponseDto extends ResponseDto {

  name: string;
  inn: string,
  dealerType: string;
  countOfContract: number;
}
