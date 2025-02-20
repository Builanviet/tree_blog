import { useState,useEffect } from "react";
import React from "react";
import { fetchUserProfileByUserId } from "../../api/userProfileApi";
import { fetchBlogComments, fetchBlogLikes } from "../../api/blogInteractionApi";
import { AiFillLike } from "react-icons/ai";
import {FaComment,FaShare } from "react-icons/fa";
import CommentItem from "../comment/comment_item";
import CreateComment from "../comment/create_comment";

export default function BlogItem({ blog,userProfile }) {

  const [user, setUser] = useState(null);
  const [like, setLike] = useState(0)
  const [comment, setComment] = useState(0)
  const [share, setShare] = useState(0)
  const [commentList, setCommentList] = useState([])

  useEffect(() => {
    const getUserProfile = async () => {
      try {
        const userData = await fetchUserProfileByUserId(blog.user.id);
        setUser(userData);
      } catch (error) {
        console.error("Error fetching user profile:", error.message);
      }
    };

    getUserProfile();
  }, [blog.user.id]);

  useEffect(() => {
    const getBlogLikes = async () => {
      try {
        const likes = await fetchBlogLikes(blog.id,"like");
        setLike(likes.data);
      } catch (error) {
        console.error("Error fetching user profile:", error.message);
      }
    };
    getBlogLikes();
  }, []);

  useEffect(() => {
    const getBlogComments = async () => {
      try {
        const likes = await fetchBlogLikes(blog.id,"comments");
        setComment(likes.data);
      } catch (error) {
        console.error("Error fetching user profile:", error.message);
      }
    };
    getBlogComments();
  }, []);

  useEffect(() => {
    const getBlogShares = async () => {
      try {
        const likes = await fetchBlogLikes(blog.id,"shares");
        setShare(likes.data);
      } catch (error) {
        console.error("Error fetching user profile:", error.message);
      }
    };
    getBlogShares();
  }, []);

  useEffect(() => {
    const getBlogComments = async () => {
      try {
        const commentList = await fetchBlogComments(blog.id);
        setCommentList(commentList.data);
      } catch (error) {
        console.error("Error fetching comments: ", error.message);
      }
    };
    getBlogComments();
  }, []);

  const handleNewCommentCreated = async () => {
    try {
      const data = await fetchBlogComments(blog.id); 
      setCommentList(data.data);
    } catch (error) {
      console.error("Error updating blog list:", error);
    }
  };


  return (
    <div style={{ 
        border: "1px solid #ddd",
        backgroundColor:"#FFFFFF", 
        maxWidth:"60%",
        margin: "10px", 
        padding: "10px",
        borderRadius: "20px" }}>
      {user && ( 
        <img
          src={user.data.avatar}
          alt="User Avatar"
          style={{ width: "50px", height: "50px", borderRadius: "50%" }}
        />
      )}
      <h2>{blog.title}</h2>
      <p>{blog.content}</p>
      <p><strong>Tags:</strong> {blog.tags}</p>
      <img
        src={blog.images}
        alt="img"
        style={{width:"100%",height:"80%"}}
        />
        <div style={{ display: "flex", alignItems: "center" }}>
            <AiFillLike style={{color:"green", height:"25px",width:"25px",margin:"10px"}}/>
            <h4>{like}</h4>
            <FaComment style={{color:"green", height:"25px",width:"25px",margin:"10px",marginLeft:"75%"}}/>
            <h4>{comment}</h4>
            <FaShare style={{color:"green", height:"25px",width:"25px",margin:"10px",marginLeft:"30px"}}/>
            <h4>{share}</h4>
        </div>
        <div style={{backgroundColor:"gray",width:"100%",height:"1px"}}></div>
        <div style={{ display: "flex", alignItems: "center", justifyContent: "space-around", margin: "10px" }}>
        <button style={{
          padding: "10px 20px",
          border: "none",
          borderRadius: "5px",
          backgroundColor: "#4CAF50",
          color: "white",
          fontSize: "16px",
          cursor: "pointer",
          transition: "background-color 0.3s",
        }}
          onMouseOver={(e) => (e.target.style.backgroundColor = "#45a049")}
          onMouseOut={(e) => (e.target.style.backgroundColor = "#4CAF50")}
        >
          Like
        </button>

        <button style={{
          padding: "10px 20px",
          border: "none",
          borderRadius: "5px",
          backgroundColor: "#2196F3",
          color: "white",
          fontSize: "16px",
          cursor: "pointer",
          transition: "background-color 0.3s",
        }}
          onMouseOver={(e) => (e.target.style.backgroundColor = "#1976D2")}
          onMouseOut={(e) => (e.target.style.backgroundColor = "#2196F3")}
        >
          Comment
        </button>

        <button style={{
          padding: "10px 20px",
          border: "none",
          borderRadius: "5px",
          backgroundColor: "#FF5722",
          color: "white",
          fontSize: "16px",
          cursor: "pointer",
          transition: "background-color 0.3s",
        }}
          onMouseOver={(e) => (e.target.style.backgroundColor = "#E64A19")}
          onMouseOut={(e) => (e.target.style.backgroundColor = "#FF5722")}
        >
          Share
        </button>
      </div>

        <div style={{backgroundColor:"gray",width:"100%",height:"1px"}}></div>
        <CreateComment key={userProfile.id} userProfile={userProfile} blog = {blog} onNewCommentCreated={handleNewCommentCreated}/>
        <div style={{ display: "flex", flexDirection: "column", alignItems: "flex-start" }}>
          {commentList.map((comment) => (
            <CommentItem key={comment.id} BlogInteraction={comment}/> 
          ))}
        </div>
    </div>
  );
}
