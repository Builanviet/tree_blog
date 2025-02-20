const API_URL = 'http://localhost:8081/api/blog_interaction'; 

export const fetchBlogLikes = async (blogId,interactionType) => {
    try {
      const response = await fetch(API_URL+"/"+blogId+"/"+interactionType, {
        method: 'GET',  
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('accessToken')}`, 
        },
      });
  
      if (!response.ok) {
        throw new Error('Failed to fetch');
      }
  
      const data = await response.json();
      return data;
    } catch (error) {
      throw error;
    }
  };

  export const fetchBlogComments = async (blogId) => {
    try {
      const response = await fetch(API_URL+"/comments/"+blogId, {
        method: 'GET',  
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('accessToken')}`, 
        },
      });
  
      if (!response.ok) {
        throw new Error('Failed to fetch');
      }
  
      const data = await response.json();
      return data;
    } catch (error) {
      throw error;
    }
  };

  export const createBlogInteraction = async (blogIntectionData) => {
    try {
      const response = await fetch(API_URL, {
        method: 'POST',  
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('accessToken')}`, 
        },
        body: JSON.stringify(blogIntectionData),  
      });
  
      if (!response.ok) {
        throw new Error('Failed to create blog interaction');
      }
  
      const data = await response.json();
      return data; 
    } catch (error) {
      throw error; 
    }
  };