export {};

declare global {
  interface IRequest {
    url: string;
    method: string;
    body?: { [key: string]: unknown };
    queryParams?: unknown;
    useCredentials?: boolean;
    headers?: unknown;
    nextOption?: unknown;
  }

  interface IBackendRes<T> {
    error?: string | string[];
    message: string;
    status: number;
    data?: T;
  }

  interface ILogin {
    user: {
      userId: number;
      name: string;
      title: string;
      email: string;
    };
    token: string;
    refreshToken: string;
  }
}
