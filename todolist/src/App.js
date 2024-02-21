import { BrowserRouter, Route, Router, Routes } from "react-router-dom";
import SignIn from "./pages/SignIn";
import Landing from "./pages/Landing";
import SignUp from "./pages/SignUp";
function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Landing />} />
        <Route path="/SignIn" element={<SignIn />} />
        <Route path="/SignUp" element={<SignUp />} />
      </Routes>
    </BrowserRouter>
  );
}
export default App;
