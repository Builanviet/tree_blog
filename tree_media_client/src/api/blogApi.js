const API_URL = 'http://localhost:8081/api/blog'; 

export const fetchUserBlog = async () => {
    try {
      const response = await fetch(API_URL+"/user", {
        method: 'GET',  
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('accessToken')}`, 
        },
      });
  
      if (!response.ok) {
        throw new Error('Failed to fetch trees');
      }
  
      const data = await response.json();
      return data;
    } catch (error) {
      throw error;
    }
  };

  export const fetchAllBlogs = async () => {
    try {
      const response = await fetch(API_URL+"/all?visibiity=FRIEND", {
        method: 'GET',  
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('accessToken')}`, 
        },
      });
  
      if (!response.ok) {
        throw new Error('Failed to fetch trees');
      }
  
      const data = await response.json();
      return data;
    } catch (error) {
      throw error;
    }
  };

  export const createBlog = async (blogData) => {
    try {
      const response = await fetch(API_URL, {
        method: 'POST',  
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('accessToken')}`, 
        },
        body: JSON.stringify(blogData),  
      });
  
      if (!response.ok) {
        throw new Error('Failed to create blog');
      }
  
      const data = await response.json();
      return data; 
    } catch (error) {
      throw error; 
    }
  };