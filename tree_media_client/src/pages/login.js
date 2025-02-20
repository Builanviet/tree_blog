import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const LoginForm = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setError(""); // Reset error

    try {
      const response = await fetch("http://localhost:8081/api/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Login failed. Please try again.");
      }

      const data = await response.json();
      console.log("Login successful:", data);
      localStorage.setItem("accessToken", data.access_token);

      navigate("/homepage");
    } catch (err) {
      console.error("Login failed:", err.message);
      setError(err.message);
    }
  };

  return (
    <div
  style={{
    backgroundImage: "url('https://media.cntraveler.com/photos/5eb18e42fc043ed5d9779733/master/pass/BlackForest-Germany-GettyImages-147180370.jpg')",
    backgroundSize: "cover",
    backgroundPosition: "center",
    backgroundRepeat: "no-repeat",
    minHeight: "100vh",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
  }}
>
  <div
    style={{
      backgroundColor: "#EEEEEE",
      maxWidth: "400px",
      margin: "50px auto",
      textAlign: "center",
      padding: "20px",
      borderRadius: "10px", 
      boxShadow: "0px 4px 8px rgba(0, 0, 0, 0.2)", 
    }}
  >
    <h2>Sign In</h2>
    <form onSubmit={handleLogin}>
      <div style={{ marginBottom: "15px" }}>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
          style={{
            width: "100%",
            padding: "10px",
            fontSize: "16px",
            boxSizing: "border-box",
            borderRadius: "10px", 
            border: "1px solid #ccc", 
          }}
        />
      </div>
      <div style={{ marginBottom: "15px" }}>
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
          style={{
            width: "100%",
            padding: "10px",
            fontSize: "16px",
            boxSizing: "border-box",
            borderRadius: "10px", 
            border: "1px solid #ccc", 
          }}
        />
      </div>
      <button
        type="submit"
        style={{
          width: "100%",
          padding: "10px",
          fontSize: "16px",
          backgroundColor: "#00CC00",
          color: "#fff",
          border: "none",
          cursor: "pointer",
          borderRadius: "10px", 
          transition: "background-color 0.3s",
        }}
      >
        SIGN IN 
      </button>
      {error && (
        <div style={{ color: "red", marginTop: "15px" }}>
          <strong>{error}</strong>
        </div>
      )}
    </form>
  </div>
</div>

  );
};

export default LoginForm;
