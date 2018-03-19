package webhoinghi.utils;

public final class PathUtils {
    //Get the file's extension, including dot, by its' path
    public static String GetFileExtension(String pathOrFilename)
    {
        try
        {
            return pathOrFilename.substring(pathOrFilename.lastIndexOf("."));
        }
        catch (Exception e)
        {
            return null;
        }
    }
}

