import { AuthError } from "next-auth";

export class CustomAuthError extends AuthError {
  static type: string;

  constructor(message?: any) {
    super();
    this.type = message;
  }
}

export class InvalidEmailPasswordError extends AuthError {
  static type: string = "Email or password is wrong.";

  constructor(message?: any) {
    super();
    this.type = message;
  }
}

export class NotFoundUserError extends AuthError {
  static type: string = "Account is not existed.";

  constructor(message?: any) {
    super();
    this.type = message;
  }
}
