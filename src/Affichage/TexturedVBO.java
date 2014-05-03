package Affichage;


import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import org.lwjgl.BufferUtils;

/**
 * Tutorial 7: Vertex Buffer Objects
 * 
 * @author Sri Harsha Chilakapati
 */

public class TexturedVBO
{
    // Texture buffer id
    int vboTextureID;
    // Vertex buffer id
    int vboVertexID;
    
    // Texture Object
    public Texture texture;
    
   
    private TexturedVBO(Texture tex, int vertID, int texID)
    {
        vboTextureID = texID;
        vboVertexID = vertID;
        texture = tex;
    }
    
   
    public static TexturedVBO loadTexturedVBO(Texture tex)
    {
        // Create buffers
        FloatBuffer vertices = BufferUtils.createFloatBuffer(2*4);
        vertices.put(new float[]
        {
            0, 0,
            tex.width, 0,
            tex.width, tex.height,
            0, tex.height
        });
        
        FloatBuffer texCoords = BufferUtils.createFloatBuffer(2*4);
        texCoords.put(new float[]
        {
            0, 0,
            1, 0,
            1, 1,
            0, 1
        });
        
        vertices.rewind();
        texCoords.rewind();
        
        // Create VBO
        int vboVertexID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboVertexID);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
        int vboTextureID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboTextureID);
        glBufferData(GL_ARRAY_BUFFER, texCoords, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
        return new TexturedVBO(tex, vboVertexID, vboTextureID);
    }
    
   
    public void render_vbo(double x, double y)
    {
        glPushMatrix();
        {
        	
            // Translate to location
            glTranslated(x, y, 0);
            
            glEnable(GL_TEXTURE_2D);
            // Bind the texture
            glBindTexture(GL_TEXTURE_2D, texture.id);

            // Bind the texture coords buffer
            glBindBuffer(GL_ARRAY_BUFFER, vboTextureID);
            glTexCoordPointer(2, GL_FLOAT, 0, 0);

            // Bind the vertex buffer
            glBindBuffer(GL_ARRAY_BUFFER, vboVertexID);
            glVertexPointer(2, GL_FLOAT, 0, 0);

            // Enable client states
            glEnableClientState(GL_VERTEX_ARRAY);
            glEnableClientState(GL_TEXTURE_COORD_ARRAY);

            // Do rendering
            glDrawArrays(GL_QUADS, 0, 4);

            // Disable client states
            glDisableClientState(GL_VERTEX_ARRAY);
            glDisableClientState(GL_TEXTURE_COORD_ARRAY);
            glDisable(GL_TEXTURE_2D);
        }
        glPopMatrix();
    }
    
    public void render(double x, double y)
    {
        glPushMatrix();
        {
            // Translate to location
            glTranslated(x+200, y+200, 0);
            
            glEnable(GL_TEXTURE_2D);
            // Bind the texture
            glBindTexture(GL_TEXTURE_2D, texture.id);

            // Bind the texture coords buffer
            glBegin(GL_QUADS);
            glVertex2i(-texture.width/2, -texture.height/2);
            glTexCoord2f(0, 0);
            glVertex2i(-texture.width/2, texture.height/2);
            glTexCoord2f(1, 0);
            glVertex2i(texture.width/2, texture.height/2);
            glTexCoord2f(1, 1);
            glVertex2i(texture.width/2, -texture.height/2);
            glTexCoord2f(0, 1);
            glEnd();
            
            glDisable(GL_TEXTURE_2D);

        }
        glPopMatrix();
    }
   
    public void dispose()
    {
        glDeleteBuffers(vboVertexID);
        glDeleteBuffers(vboTextureID);
    }

}