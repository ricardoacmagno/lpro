package LogicClient;

/**
 * <code>MD5_has</code> is the hash creator to encode the password
 *
 * @author ricar
 */
public class MD5_hash {

    /**
     * Responsible for the hash creation
     *
     * @param md5 Receives the password
     * @return hash created
     */
    public static String MD5_hash(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
