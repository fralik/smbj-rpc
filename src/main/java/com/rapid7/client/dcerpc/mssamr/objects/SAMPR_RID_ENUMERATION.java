/**
 * Copyright 2017, Rapid7, Inc.
 *
 * License: BSD-3-clause
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of the copyright holder nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 */
package com.rapid7.client.dcerpc.mssamr.objects;

import java.io.IOException;
import com.rapid7.client.dcerpc.io.PacketInput;
import com.rapid7.client.dcerpc.io.ndr.Alignment;
import com.rapid7.client.dcerpc.io.ndr.Unmarshallable;
import com.rapid7.client.dcerpc.objects.RPC_UNICODE_STRING;

/**
 * This class represents the structure returned with {@link EnumeratedDomains}.
 *
 *  typedef struct _SAMPR_RID_ENUMERATION {
 *      unsigned long RelativeId;
 *      RPC_UNICODE_STRING Name;
 *  } SAMPR_RID_ENUMERATION,
 *  *PSAMPR_RID_ENUMERATION;
 *
 * @see <a href="https://msdn.microsoft.com/en-us/library/cc245560.aspx">
 *       https://msdn.microsoft.com/en-us/library/cc245560.aspx</a>
 *
 * Alignment: 4 (Max[4, 4])
 *      unsigned long RelativeId: 4
 *      RPC_UNICODE_STRING Name: 4
 */
public class SAMPR_RID_ENUMERATION implements Unmarshallable {

    private int relativeId;
    private RPC_UNICODE_STRING name;

    public String getName() {
        return name.getValue();
    }

    public void setName(final RPC_UNICODE_STRING name) {
        this.name = name;
    }

    public int getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(int relativeId) {
        this.relativeId = relativeId;
    }

    @Override
    public void unmarshalPreamble(PacketInput in) throws IOException {
    }

    @Override
    public void unmarshalEntity(PacketInput in) throws IOException {
        // Structure Alignment
        in.align(Alignment.FOUR);
        // <NDR: unsigned long> unsigned long RelativeId;
        // Alignment: 4 - Already aligned
        relativeId = in.readInt();
        name = RPC_UNICODE_STRING.of(false);
        name.unmarshalEntity(in);
    }

    @Override
    public void unmarshalDeferrals(PacketInput in) throws IOException {
        name.unmarshalDeferrals(in);
    }
}
