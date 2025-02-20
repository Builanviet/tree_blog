import { useState } from "react"
import { createBlogInteraction } from "../../api/blogInteractionApi";

export default function CreateComment({userProfile,blog,onNewCommentCreated}){

    const [commentText, setCommentText] = useState("")

    const handleSubmit = async (e) => {
        e.preventDefault();
        const commentData = {
            comment_text: commentText, 
            blog_id: blog.id,       
            interaction_type: "COMMENT" 
          };
        try {
            const newComment = await createBlogInteraction(commentData);
            setCommentText("")
            if(onNewCommentCreated){
                onNewCommentCreated()
            }
        } catch (error) {
            console.error("Error creating  interaction:", error);
        }
    }

    return <div>
        <form onSubmit={handleSubmit}>
            <div style={{ display: "flex", alignItems: "center", gap: "10px",marginTop:"10px" }}>
                <img
                    src={userProfile.avatar}
                    alt="img"
                    style={{ width: "35px", height: "35px", borderRadius: "50%" }}
                />
                <input
                    type="text"
                    placeholder="Comment here ..."
                    value={commentText}
                        onChange={(e) => setCommentText(e.target.value)}
                    style={{
                    flex: 1,
                    padding: "10px",
                    border: "1px solid #ccc",
                    borderRadius: "5px",
                    fontSize: "14px",
                    maxWidth: "500px"
                    }}
                />
                <button type="submit" style={{width:"100px",height:"35px",margin:"10px 0",backgroundColor:"green",borderRadius:"10px"}}><h4 style={{margin:"10px 0",color:"white"}}>Comment</h4></button>
            </div>
        </form>
    </div>
}