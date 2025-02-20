export default function CommentItem({ BlogInteraction }) {
    return (
    <div style={{}}>
        <img
          src={BlogInteraction.user.user_profile.avatar}
          alt="img"
          style={{
            width: "35px",
            height: "35px",
            borderRadius: "50%",
            marginRight: "10px", // Tạo khoảng cách giữa avatar và text
            verticalAlign: "middle", // Canh giữa với văn bản
          }}
        />
        <div
        style={{
          backgroundColor: "#DDDDDD",
          display: "inline-block",
          padding: "10px", 
          borderRadius: "5px", 
          marginTop: "10px", 
          maxWidth: "100%", 
          wordWrap: "break-word", 
        }}
        >
        
        <div style={{ display: "inline-block", verticalAlign: "middle" }}>
          <p style={{ margin: 0 }}>{BlogInteraction.comment_text}</p>
          
        </div>
      </div>
      <p style={{ marginLeft: "40px", fontSize: "12px" }}>
        {new Date(
            new Date(BlogInteraction.created_at).getTime() - 7 * 60 * 60 * 1000
        ).toLocaleString("vi-VN", {
            hour: "2-digit",
            minute: "2-digit",
            day: "2-digit",
            month: "2-digit",
            year: "numeric",
        })}
        </p>
    </div>
      
    );
  }
  