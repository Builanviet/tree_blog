
import React, { useEffect, useState } from "react";
import { fetchAllBlogs,fetchUserBlog } from "../../api/blogApi"; 
import BlogItem from "../blog/blog_item"; 
import { getCurrentUserProfile } from "../../api/userProfileApi";
import CreateBlog from "../blog/create_blog";

export default function BlogList() {
  
  const [userProfile, setUserProfile] = useState("")
  const [blogs, setBlogs] = useState([]);

  useEffect(() => {
    const fetchBlogs = async () => {
      try {
        const data = await fetchAllBlogs();
        setBlogs(data.data);
      } catch (error) {
        console.error("Error fetching blogs:", error);
      }
    };

    fetchBlogs();
  }, []);

  useEffect(() => {
    const getCurrentProfile = async () => {
      try {
        const currentUserProfile = await getCurrentUserProfile();
        setUserProfile(currentUserProfile.data);
      } catch (error) {
        console.error("Error fetching blogs:", error);
      }
    };

    getCurrentProfile();
  }, []);

  const handleNewBlogCreated = async () => {
    try {
      const data = await fetchAllBlogs(); 
      setBlogs(data.data);
    } catch (error) {
      console.error("Error updating blog list:", error);
    }
  };

  return (
    <div style={{marginLeft:"0px"}}>
      <h1>Homepage</h1>
      <CreateBlog key={userProfile.id} userProfile={userProfile} onNewBlogCreated={handleNewBlogCreated}/>
      {blogs.map((blog) => (
        <BlogItem key={blog.id} blog={blog} userProfile={userProfile}/> 
      ))}
    </div>
  );
}
