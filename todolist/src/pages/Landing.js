import { Link } from "react-router-dom";

function Landing() {
  return (
    <>
      <button>
        <Link to="/SignIn">로그인</Link>
      </button>
      <button>
        <Link to="/SignUp">회원가입</Link>
      </button>
    </>
  );
}

export default Landing;
