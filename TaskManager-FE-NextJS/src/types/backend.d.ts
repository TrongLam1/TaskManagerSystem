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
    user: IUser;
    token: string;
    refreshToken: string;
  }

  interface IUser {
    userId: number;
    createdAt: Date;
    name: string;
    title: string;
    email: string;
    roles: string[];
    active: boolean;
  }
}
