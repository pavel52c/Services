export type BaseReq<T = unknown> = {
  status: number;
  data: T;
}

export const prepareResp = <T>(respData: T, status: number = 200): BaseReq<T> => ({
  status,
  data: respData
})
