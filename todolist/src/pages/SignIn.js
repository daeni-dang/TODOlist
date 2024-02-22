import { useState } from "react";
import { Link } from "react-router-dom";

import logo from "../Image/weekly.svg";
import eyeOff from "../Image/eye-off.svg";
import eyeOn from "../Image/eye-on.svg";

import "../Style/Sign.css";

const emailRegex =
  /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
const pwdRegex = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$/;

function SignIn() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");

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
        <div className="title">
          <img className="logo" src={logo} alt="logo" />
          <div className="toSignIn_Up">
            <span>계정이 없으신가요요?</span>
            <Link to="/Signup" className="toMove">
              회원가입 하기
            </Link>
          </div>
        </div>
        <form className="signForm">
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
                  setPassword(e.target.value);
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
          <button id="signup" type="submit">
            회원가입
          </button>
        </form>
      </div>
    </div>
  );
}

export default SignIn;
