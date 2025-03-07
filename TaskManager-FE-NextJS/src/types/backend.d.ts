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
    statusCode: number | string;
    data?: T;
  }

  interface ILogin {
    user: {
      id: number;
      name: string;
      email: string;
      imageUrl: string;
    };
    access_token: string;
    refresh_token: string;
  }
}
