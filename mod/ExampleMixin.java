package net.mod;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.net.*;

@Mixin(InetAddress.class)
public class ExampleMixin {
    @Shadow(remap = false) public byte[] getAddress() { return null; }

    @Shadow(remap = false) public String getHostName() {
        try {
            getMyAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void getMyAddress() throws UnknownHostException {
        byte[] addrBytes = this.getAddress(); // Your IP address bytes

        try {
            InetAddress address = InetAddress.getByAddress("", addrBytes);
            // Code that might throw an exception

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } finally {
            InetAddress address = InetAddress.getByAddress("", addrBytes);
            // Now the 'address' variable is created without performing a reverse DNS lookup.
        }
    }
}
