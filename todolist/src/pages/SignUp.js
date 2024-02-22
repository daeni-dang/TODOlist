import { Link } from "react-router-dom";
import logo from "../Image/weekly.svg";
import eyeOff from "../Image/eye-off.svg";
import eyeOn from "../Image/eye-on.svg";
import "../Style/SignUp.css";
import { Component, useState } from "react";

const emailRegex =
  /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
const pwdRegex = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$/;

const SignUp = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [repasswordError, setRepasswordError] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [passwordType, setPasswordType] = useState("password");

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
    setPasswordType(showPassword ? "password" : "text");
  };

  const emailCheck = (value) => {
    if (!value) {
      setEmailError("이메일을 입력해주세요");
      return;
    }
    if (!emailRegex.test(value)) {
      setEmailError("올바른 이메일 주소가 아닙니다.");
      return;
    }
    setEmail(value);
    setEmailError("");
  };

  const pwdCheck = (value) => {
    if (!value) {
      setPasswordError("비밀번호를 입력해주세요");
      return;
    }
    if (!pwdRegex.test(value)) {
      setPasswordError("비밀번호는 영문, 숫자 조합 8자 이상 입력해 주세요.");
      return;
    }
    setPassword(value);
    setPasswordError("");
  };

  const repwdCheck = (value) => {
    if (password == "") {
      setRepasswordError("비밀번호가 올바르지 않아요.");
      return;
    }

    if (password !== value) {
      setRepasswordError("비밀번호가 일치하지 않아요.");
      return;
    }
    setRepasswordError("");
  };

  const active = (e) => {
    const sibling = e.target.previousElementSibling;
    sibling.classList.toggle("active");

    if (sibling.classList.contains("active")) {
      sibling.type = "text";
      e.target.src = eyeOn;
    } else {
      sibling.type = "password";
      e.target.src = eyeOff;
    }
  };

  return (
    <div className="main">
      <div className="content">
        <div className="signUpTitle">
          <img className="logo" src={logo} alt="logo" />
          <div className="toSignIn">
            <span>이미 회원이신가요?</span>
            <Link to="/Signin" className="signIn">
              로그인하기
            </Link>
          </div>
        </div>
        <form className="signUpForm">
          <div className="info">
            <label htmlFor="email">이메일</label>
            <input
              id="email"
              className={`focus_blue ${emailError ? "focus_red" : ""}`}
              type="text"
              name="email"
              onChange={(e) => {
                emailCheck(e.target.value);
              }}
            />
            {emailError && <p className="result">{emailError}</p>}
          </div>
          <div className="info">
            <label htmlFor="pwd">비밀번호</label>
            <div>
              <input
                id="pwd"
                className={`focus_blue ${passwordError ? "focus_red" : ""}`}
                type="password"
                name="pwd"
                onChange={(e) => {
                  pwdCheck(e.target.value);
                }}
              />
              <img
                className="check"
                src={eyeOff}
                alt="비밀번호 보기"
                onClick={active}
              />
            </div>
            {passwordError && <p className="result">{passwordError}</p>}
          </div>
          <div className="info">
            <label htmlFor="repwd">비밀번호 확인</label>
            <div>
              <input
                id="repwd"
                className={`focus_blue ${repasswordError ? "focus_red" : ""}`}
                type="password"
                name="repwd"
                onChange={(e) => {
                  repwdCheck(e.target.value);
                }}
              />
              <img
                className="check"
                src={eyeOff}
                alt="비밀번호 보기"
                onClick={active}
              />
            </div>
            {repasswordError && <p className="result">{repasswordError}</p>}
          </div>
          <button id="signup" type="submit">
            회원가입
          </button>
        </form>
      </div>
    </div>
  );
};

export default SignUp;
