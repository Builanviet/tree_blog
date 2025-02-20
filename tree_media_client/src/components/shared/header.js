import React from "react";

const Header = () => {
  return (
    <header
      style={{
        position: "fixed", // Fixed position
        top: 0, // Stick to the top of the viewport
        left: 0,
        width: "100%", // Full width
        zIndex: 1000, // Ensure it appears on top of other elements
        backgroundColor: "#f8f9fa", // Background color
        padding: "10px 20px",
        boxShadow: "0px 4px 6px rgba(0, 0, 0, 0.1)", // Add a shadow for better visibility
      }}
    >
      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
        }}
      >
  
        <div style={{ fontSize: "20px", fontWeight: "bold" }}>
          <img
            src="https://t4.ftcdn.net/jpg/01/38/18/51/360_F_138185102_HcpDywocbX7A4XE4J5xFMvB2P2Dq9xIH.jpg"
            alt="Logo"
            style={{ height: "40px", verticalAlign: "middle" }}
          />
          QTerranium
        </div>

        <nav>
          <ul
            style={{
              display: "flex",
              gap: "50px",
              listStyle: "none",
              margin: 0,
              padding: 0,
            }}
          >
            <li>
              <a
                href="/account"
                style={{
                  textDecoration: "none",
                  color: "#007BFF",
                  fontWeight: "bold",
                }}
              >
                My Account
              </a>
            </li>
            <li>
              <a
                href="/network"
                style={{
                  marginRight: "50px",
                  textDecoration: "none",
                  color: "#007BFF",
                  fontWeight: "bold",
                }}
              >
                Network
              </a>
            </li>
          </ul>
        </nav>
      </div>
    </header>
  );
};

export default Header;
