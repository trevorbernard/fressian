//   Copyright (c) Metadata Partners, LLC. All rights reserved.
//   The use and distribution terms for this software are covered by the
//   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
//   which can be found in the file epl-v10.html at the root of this distribution.
//   By using this software in any fashion, you are agreeing to be bound by
//   the terms of this license.
//   You must not remove this notice, or any other, from this software.

package org.fressian.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class FixedBytesOutputStream extends OutputStream {
    private byte[] buf;
    private int count;

    public FixedBytesOutputStream(byte[] buf) {
        this.buf = buf;
    }

    public byte[] internalBuffer() {
        return buf;
    }

    public int length() {
        return count;
    }

    public byte toByteArray()[] {
        return Arrays.copyOf(buf, count);
    }

    public void write(int b) {
        buf[count] = (byte) b;
        count += 1;
    }

    public void write(byte b[], int off, int len) {
        if ((off < 0) || (off > b.length) || (len < 0) || ((off + len) - b.length > 0)) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(b, off, buf, count, len);
        count += len;
    }

    public void reset() {
        count = 0;
    }

    public int size() {
        return count;
    }

    public String toString() {
        return new String(buf, 0, count);
    }

    public String toString(String charsetName) throws UnsupportedEncodingException {
        return new String(buf, 0, count, charsetName);
    }

    public void writeTo(OutputStream out) throws IOException {
        out.write(buf, 0, count);
    }
}
